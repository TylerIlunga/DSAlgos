'use strict';
const CryptoJS = require('crypto-js'),
      express = require('express'),
      bodyParser = require('body-parser'),
      WebSocket = require('ws');

let httpPort = process.env.HTTP_PORT || 4000;
let p2pPort = process.env.P2P_PORT || 2000;
let initialPeers = process.env.PEERS ? process.env.PEERS.split(',') : [];

let sockets = [];
const MessageType = {
  QUERY_LATEST: 0,
  QUERY_ALL: 1,
  RESPONSE_BLOCKCHAIN: 2
}

class Block {
  constructor(index, previousHash, timestamp, data, hash) {
    this.index = index;
    this.previousHash = previousHash.toString();
    this.timestamp = timestamp;
    this.data = data;
    this.hash = hash.toString();
  }
};

const getGenesisBlock = () => {
    const genesisIndex = 0;
    const genesisPreviousHash = "0";
    const genesisTimeStamp = 1465154705;
    const genesisData = "bootup";
    const genesisHash = "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7";

    return new Block(genesisIndex, genesisPreviousHash, genesisTimeStamp, genesisData, genesisHash);
};

let blockchain = [getGenesisBlock()];

const calculateHash = (index, previousHash, timestamp, data) => {
  return CryptoJS.SHA256(index + previousHash, + timestamp + data).toString()
};

const calculateHashForBlock = (block) => {
  return calculateHash(block.index, block.previousHash, block.timestamp, block.data);
}

const getLatestBlock = () => blockchain[blockchain.length - 1];

const generateNextBlock = (blockData) => {
  let previousBlock = getLatestBlock();
  let nextIndex = previousBlock.index + 1;
  let nextTimeStamp = new Date().getTime() / 1000;
  var nextHash = calculateHash(nextIndex, previousBlock.hash, nextTimeStamp, blockData);

  return new Block(nextIndex, previousBlock.hash, nextTimeStamp, blockData, nextHash);
};

const blockValidation = (newBlock, previousBlock) => {
  if (previousBlock.index + 1 !== newBlock.index) {
    console.log('Invalid index');
    return false;
  } else if (previousBlock.hash !== newBlock.previousHash) {
    console.log('Previous hash does not match');
    return false;
  } else if (calculateHashForBlock(newBlock) !== newBlock.hash) {
    console.log(`Invalid hash: ${calculateHashForBlock(newBlock)}\nFor hash: ${newBlock.hash}`);
    return false;
  }

  return true;
};

const chainValidation = (blockchain) => {
  if (JSON.stringify(blockchain[0]) !== JSON.stringify(getGenesisBlock())) {
    return false;
  }

  var temporaryBlocks = [blockchain[0]];
  for (var i = 1; i < blockchain.length; i++) {
    if (blockValidation(blockchain[i], temporaryBlocks[i - 1])) {
      temporaryBlocks.push(blockchain[i]);
    } else {
      return false
    }
  }

  return true;
}

var appendBlock = (newBlock) => {
  if (blockValidation(newBlock, getLatestBlock())) {
    blockchain.push(newBlock);
  }
};

var write = (webSocket, message) => webSocket.send(JSON.stringify(message));
var broadcast = (message) => sockets.forEach(socket => write(socket, message));

const chainReplacement = (newBlockchain) => {
  if (chainValidation(newBlockchain) && newBlockchain.length > blockchain.length) {
    console.log('Inputted blockchain is valid. Replaing current blockchain with inputted blockchain');
    blockchain = newBlockchain;
    broadcast(responseLatestMessage());
  } else {
    console.log('Inputted blockchain is invalid');
  }
};

const handleBlockchainResponse = (message) => {
  let blocksReceived = JSON.parse(message.data).sort((b1, b2) => (b1.index - b2.index));
  let latestBlocksReceived = blocksReceived[blocksReceived.length - 1];
  let latestBlockInPossession = getLatestBlock();

  if (latestBlocksReceived.index > latestBlockInPossession.index) {
    console.log(`Chance of blockchain being behind.\nReceived: ${latestBlockInPossession.index}\nPeer received: ${latestBlocksReceived.index}`);
    if (latestBlockInPossession.hash === latestBlocksReceived.previousHash) {
      //We can append the received block to our chain
      blockchain.push(latestBlocksReceived);
      broadcast(responseLatestMessage());
    } else if (blocksReceived.length === 1) {
      //We have to query the chain from our Peer
      broadcast(queryAllMessage());
    } else {
      console.log('Received blockchain is longer than current blockchain.\nReplacement in process...');
      chainReplacement(blocksReceived);
    }
  } else {
    console.log('Received blockchain is not longer than current blockchain.');
  }
};

let queryChainLengthMessage = () => ({'type': MessageType.QUERY_LATEST});
let queryAllMessage = () => ({'type': MessageType.QUERY_ALL});
let responseChainMessage = () => ({
  'type': MessageType.RESPONSE_BLOCKCHAIN,
  'data': JSON.stringify(blockchain)
})
let responseLatestMessage = () => ({
  'type': MessageType.RESPONSE_BLOCKCHAIN,
  'data': JSON.stringify([getLatestBlock()])
})

let MessageHandler = (webSocket) => {
  webSocket.on('message', (data) => {
    let message = JSON.parse(data);
    console.log(`Message Received:\n${JSON.stringify(message)}`);
    switch (message.type) {
      case MessageType.QUERY_LATEST:
        write(webSocket, responseLatestMessage());
        break;
      case MessageType.QUERY_ALL:
        write(webSocket, responseChainMessage());
        break;
      case MessageType.RESPONSE_BLOCKCHAIN:
        handleBlockchainResponse(message);
        break;
    }
  });
};

let ErrorHandler = (webSocket) => {
  let closeConnection = (webSocket) => {
    console.log(`Failed connection to Peer: ${webSocket.url}`);
    sockets.splice(sockets.indexOf(webSocket), 1);
  }
  webSocket.on('close', () => closeConnection(webSocket));
  webSocket.on('error', () => closeConnection(webSocket));
}

let initConnection = (webSocket) => {
  sockets.push(webSocket);
  MessageHandler(webSocket);
  ErrorHandler(webSocket);
  write(webSocket, queryChainLengthMessage());
};

let peerConnection = (newPeers) => {
  newPeers.forEach((peer) => {
    var webSocket = new WebSocket(peer);
    webSocket.on('open', () => initConnection(webSocket));
    webSocket.on('error', () => console.log('Connection failed'));
  })
};

const proofOfWork = (input) => {
  
}



let httpServer = () => {
  let app = express();
  app.use(bodyParser.json());

  app.get('/blocks', (req, res) => {
    res.send(JSON.stringify(blockchain));
  });

  app.get('/peers', (req, res) => {
    res.send(sockets.map(s => s._socket.remoteAddress + ':' + s._socket.remotePort));
  });

  app.post('/mine', (req, res) => {
    let newBlock = generateNextBlock(req.body.data);
    appendBlock(newBlock);
    broadcast(responseLatestMessage());
    console.log('Block appended: ' + JSON.stringify(newBlock));
    res.send();
  });

  app.post('/addPeer', (req, res) => {
    peerConnection([req.body.peer]);
    res.send();
  });

  app.listen(httpPort, () => console.log(`Listening on HTTP Port ${httpPort}`));
};

let p2pServer = () => {
  let server = new WebSocket.Server({port: p2pPort});
  server.on('connection', ws => initConnection(ws));
  console.log(`Listening on WebSocket Port ${p2pPort}`);
};

peerConnection(initialPeers);
httpServer();
p2pServer();
