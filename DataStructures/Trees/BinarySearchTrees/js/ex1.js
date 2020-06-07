const f = v => {
  if (v <= 2) {
    return v;
  } else {
    v = v * f(v - 1);
  }
};

class BinarySearchTree {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

BinarySearchTree.prototype.insert = function(value) {
  if (value <= this.value) {
    if (!this.left) {
      this.left = new BinarySearchTree(value);
    } else {
      this.left.insert(value);
    }
  } else if (value > this.value) {
    if (!this.right) {
      this.right = new BinarySearchTree(value);
    } else {
      this.right.insert(value);
    }
  }
};

BinarySearchTree.prototype.contains = function(value) {
  const located = `${value} was located`;
  const notLocated = `${value} was not located`;

  if (value === this.value) {
    return located;
  } else if (value > this.value) {
    if (!this.right) {
      return notLocated;
    } else {
      this.right.contains(value);
    }
  } else if (value < this.value) {
    if (!this.left) {
      return notLocated;
    } else {
      this.left.contains(value);
    }
  }
};

BinarySearchTree.prototype.depthFirstTraversal = function(iterFunction, order) {
  let currentNode = this.value;

  if (order == 'pre-order') {
    iterFunction(currentNode);
  }

  if (this.left) {
    this.left.depthFirstTraversal(iterFunction, order);
  }

  if (order == 'this-order') {
    iterFunction(currentNode);
  }

  if (this.right) {
    this.right.depthFirstTraversal(iterFunction, order);
  }

  if (order == 'post-order') {
    iterFunction(currentNode);
  }
};

const depthFunction = log => {
  console.log(log);
};

BinarySearchTree.prototype.breadthFirstTraversal = function(iterFunction) {
  let queue = [this];

  while (queue.length) {
    let treeNode = queue.shift();
    iterFunction(treeNode);

    if (treeNode.left) {
      queue.push(treeNode.left);
    }

    if (treeNode.right) {
      queue.push(treeNode.right);
    }
  }
};

const breadthFunction = node => {
  console.log(node.value);
};

BinarySearchTree.prototype.getMin = function() {
  if (this.left) {
    this.left.getMin();
  } else {
    return this.value;
  }
};

BinarySearchTree.prototype.getMax = function() {
  if (this.right) {
    this.right.getMax();
  } else {
    return this.value;
  }
};
