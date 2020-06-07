class BST {
  constructor(value) {
    if (!value || value < 0) {
      throw new Error("You must pass in an value greater than 0");
    }
    this.value = value;
    this.left = null;
    this.right = null;
  }

  insert(value) {
    if (value <= this.value) {
      if (!this.left) {
        this.left = new BST(value);
      } else {
        this.left.insert(value);
      }
    } else if (value > this.value) {
      if (!this.right) {
        this.right = new BST(value);
      } else {
        this.right.insert(value);
      }
    }
  }

  contains(value) {
    const located = `${value} was located!`;
    const notLocated = `${value} was not located!`;

    if (value === this.value) {
      return located;
    } else if (value <= this.value) {
      if (!this.left) {
        return notLocated;
      } else {
        return this.left.contains(value);
      }
    } else if (value > this.value) {
      if (!this.right) {
        return notLocated;
      } else {
        return this.right.contains(value);
      }
    }
  }

  depthFirstTraversal(logfunc, order) {
    if (order === "pre-order") {
      logfunc(this.value);
    }

    if (this.left) {
      console.log(`in here!`);
      return this.left.depthFirstTraversal(logfunc, order);
    }

    if (order === "in-order") {
      logfunc(this.value);
    }

    if (this.right) {
      console.log(`in there!`);
      return this.right.depthFirstTraversal(logfunc, order);
    }

    if (order === "post-order") {
      logfunc(this.value);
    }
  }

  breadthFirstTraversal(logfunc) {
    let queue = [this];

    while (queue.length) {
      let treeNode = queue.shift();
      logfunc(treeNode);
      if (treeNode.left) {
        queue.push(treeNode.left);
      }
      if (treeNode.right) {
        queue.push(treeNode.right);
      }
    }
  }

  getMinVal() {
    if (this.left) {
      return this.left.getMinVal();
    } else {
      return this.value;
    }
  }

  getMaxVal() {
    if (this.right) {
      return this.right.getMaxVal();
    } else {
      return this.value;
    }
  }
}

const tree = new BST(3);
// console.log(tree);
tree.insert(2);
// console.log(tree);
tree.insert(1);
// console.log(tree);
tree.insert(5);
// console.log(tree);
tree.insert(4);
tree.insert(10);
console.log(tree);
// console.log(tree.contains(3));
// console.log(tree.depthFirstTraversal(value => console.log(value), "pre-order"));
// console.log(tree.depthFirstTraversal(value => console.log(value), "in-order"));
// console.log(tree.depthFirstTraversal(value => console.log(value), "post-order"));
// console.log(tree.breadthFirstTraversal(node => console.log(node.value)));
// console.log(tree.getMinVal());
// console.log(tree.getMaxVal());
