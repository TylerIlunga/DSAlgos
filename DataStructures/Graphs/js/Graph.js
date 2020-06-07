const Queue = require('../../Queue/js/Queue');
const PriorityQueue = require('../../PriorityQueue/js/PriorityQueue');
const Stack = require('../../Stack/js/Stack');

/**
 * Graph Data Structure
 */

class Graph {
  constructor(dataType, isDAG = false) {
    this.isValidType(dataType);
    this.dataType = dataType;
    this.isDAG = isDAG;
    this.nodes = [];
    this.edges = {};
    this.logging = true;
    this.printGraph();
  }

  /**
   * isValidType
   * @param {Any} object
   */
  isValidType(object) {
    const validDataTypes = [
      'number',
      'boolean',
      'string',
      'symbol',
      'function',
      'object',
    ];
    if (!validDataTypes.includes(object)) {
      throw new Error(
        `Invalid data type: ${typeof dataType}\nReview (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/typeof)`,
      );
    }
  }

  /**
   * addNode
   * @param {Object} node
   */
  addNode(node) {
    if (typeof node.node !== this.dataType) {
      throw new Error('Nodes must match data type:', this.dataType);
    }
    this.nodes.push(node);
    this.edges[node] = [];
    this.printGraph();
  }

  /**
   * matchesAssignedDataType
   * @param {Object} fromNode
   * @param {Object} toNode
   */
  matchesAssignedDataType(fromNode, toNode = null) {
    if (!fromNode || (toNode && !(fromNode && toNode))) {
      throw new Error('Node(s) can not be null');
    }
    return fromNode && toNode
      ? typeof fromNode === this.dataType && typeof toNode === this.dataType
      : typeof fromNode === this.dataType;
  }

  /**
   * validEdges
   * @param {Object} fromNode
   * @param {Object} toNode
   * @param {boolean} directed
   */
  validEdges(fromNode, toNode, directed) {
    if (!(fromNode && toNode)) {
      throw new Error("A 'From' node and 'To' node is required.");
    }
    if (!this.matchesAssignedDataType(fromNode.node, toNode.node)) {
      throw new Error('Nodes must match data type:', this.dataType);
    }
    if (!directed && fromNode === toNode) {
      throw new Error('From and To node are the same');
    }
    // if (!directed && !this.nodes.includes(fromNode)) {
    //   throw new Error('One node(or both) does not exist within the graph');
    // }
    // if (!this.nodes.includes(fromNode)) {
    //   throw new Error('Node does not exist within the graph');
    // }
  }

  /**
   * addEdge
   * @param {Object} fromNode
   * @param {Object} toNode
   * @param {Number} weight
   */
  addEdge(fromNode, toNode, weight = 1) {
    if (this.isDAG) {
      throw new Error('This graph is a DAG.');
    }
    this.validEdges(fromNode, toNode, false);
    this.edges[fromNode].push(toNode);
    this.edges[toNode].push(fromNode);
    this.printGraph();
  }

  /**
   * addDirectedEdge
   * @param {Object} fromNode
   * @param {Object} toNode
   * @param {Number} weight
   */
  addDirectedEdge(fromNode, toNode, weight = 1) {
    if (!this.isDAG) {
      throw new Error('This graph is not a DAG.');
    }
    this.validEdges(fromNode, toNode, true);
    this.edges[fromNode].push({ node: toNode, weight });
    this.printGraph();
  }

  /**
   * DFS (Depth First Search)
   */
  DFS() {
    let stackFrontier = new Stack('object');
    let visited = new Set();
    stackFrontier.push(this.nodes[0]);
    stackFrontier.disableLogging();
    while (!stackFrontier.isEmpty()) {
      const currentNode = stackFrontier.pop();
      if (!visited.has(currentNode)) {
        visited.add(currentNode);
        console.log('Node: ', currentNode);
        this.edges[currentNode].forEach(neighbor => {
          stackFrontier.push(neighbor);
        });
      }
    }
  }

  /**
   * BFS (Breadth First Search)
   */
  BFS() {
    let queueFrontier = new Queue('object');
    let visited = new Set();
    queueFrontier.enqueue(this.nodes[0]);
    queueFrontier.disableLogging();
    while (!queueFrontier.isEmpty()) {
      const currentNode = queueFrontier.dequeue();
      if (!visited.has(currentNode)) {
        visited.add(currentNode);
        console.log('Node: ', currentNode);
        this.edges[currentNode].forEach(neighbor => {
          queueFrontier.enqueue(neighbor);
        });
      }
    }
  }

  /**
   * tsHelper
   * @param {Any} node
   * @param {Set} explored
   * @param {Queue} queue
   */
  tsHelper(node, explored, queue) {
    explored.add(node);
    this.edges[node].forEach(neighbor => {
      if (!explored.has(neighbor)) {
        this.tsHelper(neighbor, explored, queue);
      }
    });
    queue.enqueue(node);
  }

  /**
   * topologicialSort
   */
  topologicialSort() {
    if (!this.isDAG) {
      throw new Error(
        'The Topological Sort operation can only occur on Directed Graph Types.',
      );
    }
    const queue = new Queue('object');
    const explored = new Set();
    queue.disableLogging();
    this.nodes.forEach(node => {
      if (!explored.has(node)) {
        this.tsHelper(node, explored, queue);
      }
    });
    while (!queue.isEmpty()) {
      console.log('Node: ', queue.dequeue().node);
    }
  }

  /**
   * enableLogging
   */
  enableLogging() {
    if (this.logging) return;
    this.logging = true;
    console.log('enabled.');
  }

  /**
   * disableLogging
   */
  disableLogging() {
    if (!this.logging) return;
    this.logging = false;
    console.log('disabled.');
  }

  /**
   * printGraph
   */
  printGraph() {
    if (!this.logging) return;
    console.log('Graph:');
    if (this.nodes.length === 0) {
      return console.log('\tNo Nodes.');
    }
    this.nodes.forEach((node, nIndex) => {
      console.log(`\tNode ${nIndex}:`);
      if (this.edges[node] && this.edges.length === 0) {
        return console.log('\t\tNo Edges.');
      }
      if (this.edges[node]) {
        this.edges[node].forEach((neighbor, nIndex) => {
          console.log(`\t\tNeighbor ${nIndex}:`, neighbor);
        });
      }
    });
  }
}

let f1 = {
  node: (() => 'f1()')(),
  weight: 1,
};
let f2 = {
  node: (() => 'f2()')(),
  weight: 1,
};
let f3 = {
  node: (() => 'f3()')(),
  weight: 1,
};
let f4 = {
  node: (() => 'f4()')(),
  weight: 1,
};
let f5 = {
  node: (() => 'f5()')(),
  weight: 1,
};
let f6 = {
  node: (() => 'f6()')(),
  weight: 1,
};
let f7 = {
  node: (() => 'f7()')(),
  weight: 1,
};
let f8 = {
  node: (() => 'f8()')(),
  weight: 1,
};

let g = new Graph('string', true);
g.addNode(f1);
g.addNode(f2);
g.addNode(f3);
g.addNode(f4);

g.addNode(f5);
g.addNode(f6);
g.addNode(f7);
g.addNode(f8);

g.addDirectedEdge(f1, f2);
g.addDirectedEdge(f1, f3);
g.addDirectedEdge(f1, f4);
g.addDirectedEdge(f2, f4);
g.addDirectedEdge(f3, f5);
g.addDirectedEdge(f3, f6);
g.addDirectedEdge(f2, f5);
g.addDirectedEdge(f3, f7);
g.addDirectedEdge(f3, f8);
g.addDirectedEdge(f4, f8);
// g.DFS();
// g.BFS();
g.topologicialSort();
