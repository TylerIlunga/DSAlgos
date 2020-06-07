//Binary Search Trees
//Collection of nodes that are all connected together in a certain way


//Recursion
const factorial = (num) => {
  //base case
  //More efficient
  if (num <= 2) {
    return num;
  } else {
    return num * factorial(num - 1);
  }
}
console.log(factorial(5));

class BST {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

BST.prototype.insert = function (value) {
  if (value <= this.value) {
    if (!this.left) {
      this.left = new BST(value)
    } else {
      this.left.insert(value)
    }
  } else if(value > this.value) {
    if (!this.right) {
      this.right = new BST(value)
    } else {
      this.right.insert(value)
    }
  }
};

BST.prototype.contains = function (value) {
  const located = `${value} was located`;
  const notLocated = `${value} was not located`;

  if (value === this.value) {
    return located;
  } else if (value < this.value) {
    if (!this.left) {
      return notLocated;
    } else {
      return this.left.contains(value)
    }
  } else if (value > this.value) {
    if (!this.right) {
      return notLocated;
    } else {
      return this.right.contains(value);
    }
  }

};


//travels through all nodes and run log function on all of them
//order: allows us to manage the output of data
BST.prototype.depthFirstTraversal = function(iterFunc, order) {
  if (order === 'pre-order') {
    iterFunc(this.value);
  }

  if (this.left) {
    this.left.depthFirstTraversal(iterFunc, order);
  }

  //from least to greatest
  if (order === 'in-order') {
      iterFunc(this.value);
  }

  if (this.right) {
    this.right.depthFirstTraversal(iterFunc, order);
  }

  //after processing
  if (order === 'post-order') {
    iterFunc(this.value)
  }

};

//moves down tree level by level
//useful: Defines hierarchy or level of command
BST.prototype.breadthFirstTraversal = function(iterFunc) {
  //this: root node of BST
  let queue = [this];
  while (queue.length) {
    //shift(): removes the first element of the array
    let treeNode = queue.shift();
    iterFunc(treeNode);
    if (treeNode.left) {
      queue.push(treeNode.left);
    }
    if (treeNode.right) {
      queue.push(treeNode.right);
    }
  }
}

BST.prototype.getMinVal = function () {
  if (this.left) {
    return this.left.getMinVal();
  } else {
    return this.value;
  }
};

BST.prototype.getMaxVal = function () {
  if (this.right) {
    return this.right.getMaxVal();
  } else {
    return this.value;
  }
};

let tree = new BST(50);

tree.insert(30);
tree.insert(100);
tree.insert(200);
tree.insert(20);
tree.insert(10);
tree.insert(300);
console.log(tree);
let a = tree.contains(50);
let b = tree.contains(200);
let c = tree.contains(100000);
console.log(`First: ${a}\nSecond: ${b}\nThrid: ${c}`);

const depthLog = (value) => {
  console.log(value);
}

const breadthLog = (node) => {
  console.log(node.value);
}

console.log('DepthFirstTraversal:');
console.log('IN-ORDER:');
tree.depthFirstTraversal(depthLog, 'in-order');
console.log('PRE-ORDER:');
tree.depthFirstTraversal(depthLog, 'pre-order');
console.log('POST-ORDER:');
tree.depthFirstTraversal(depthLog, 'post-order');

console.log('BreadthFirstTraversal:');
tree.breadthFirstTraversal(breadthLog)

console.log(`MIN: ${tree.getMinVal()}`);
console.log(`MAX: ${tree.getMaxVal()}`);
