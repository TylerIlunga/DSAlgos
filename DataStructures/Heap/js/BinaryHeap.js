/**
 * Binary Heap Data Structure
 */
class BinaryHeap {
  constructor(dataType = null) {
    this.isValidType(dataType);
    this.dataType = dataType;
    this.heap = new Array();
    this.logging = true;
  }

  /**
   * isValidType
   * @param {Any} object
   */
  isValidType(object) {
    if (typeof object === "undefined" || typeof object === null) {
      throw new Error(`Invalid data type: ${typeof dataType}`);
    }
  }

  isValidIndex(index) {
    if (index < 0 || index > this.heap.length - 1) {
      throw new Error("Invalid index");
    }
  }

  getParent(index) {
    console.log(index);
    this.isValidIndex(index);
    return Math.floor((index - 1) / 2);
  }

  getChild(index, isRight = false) {
    this.isValidIndex(index);
    return Math.floor(index * 2 + 1 + (isRight ? 1 : 0));
  }

  /**
   * isEmpty
   * @returns {boolean}
   */
  isEmpty() {
    return this.heap.length === 0;
  }

  /**
   * insert
   * @param {Any} object
   */
  insert(object) {
    this.heap.push(object);
    this.bubbleUp(this.heap.length - 1);
    this.printHeap();
  }

  /**
   * remove
   */
  remove() {
    this.heap.shift();
    this.heap.unshift(this.heap[this.heap.length - 1]);
    this.heap.pop();
    this.bubbleDown(0);
    this.printHeap();
  }

  /**
   * hasHighPriority
   * @param {Any} parent
   * @param {Any} child
   */
  hasHighPriority(parent, child) {
    // Handle all varients.
    console.log(parent, child);
    if (typeof parent !== typeof child) {
      throw new Error("Parent and child must have the same type.");
    }
    switch (typeof parent) {
      case "number":
        return parent < child;
    }
  }

  /**
   * bubbleUp
   */
  bubbleUp(index) {
    if (index === 0) return;
    let parentIndex = this.getParent(index);
    if (this.hasHighPriority(this.heap[parentIndex], this.heap[index])) {
      let temp = this.heap[index];
      this.heap[index] = this.heap[parentIndex];
      this.heap[parentIndex] = temp;
      this.bubbleUp(parentIndex);
    }
  }

  /**
   * bubbleDown
   */
  bubbleDown(index) {
    const leftChildIndex = this.getChild(index, false);
    const rightChildIndex = this.getChild(index, true);
    const leftChild = this.heap[leftChildIndex];
    const rightChild = this.heap[rightChildIndex];
    if (
      !(leftChild && rightChild) ||
      (this.heap[index] > leftChild && this.heap[index] > rightChild)
    ) {
      return;
    }
    if (this.hasHighPriority(this.heap[index], leftChild)) {
      let temp = this.heap[index];
      this.heap[index] = this.heap[leftChildIndex];
      this.heap[leftChildIndex] = temp;
      this.bubbleDown(leftChildIndex);
    } else {
      let temp = this.heap[index];
      this.heap[index] = this.heap[rightChildIndex];
      this.heap[rightChildIndex] = temp;
      this.bubbleDown(rightChildIndex);
    }
  }

  /**
   * enableLogging
   */
  enableLogging() {
    if (this.logging) return;
    this.logging = true;
    console.log("enabled.");
  }

  /**
   * disableLogging
   */
  disableLogging() {
    if (!this.logging) return;
    this.logging = false;
    console.log("disabled.");
  }

  /**
   * printHeap
   */
  printHeap() {
    if (!this.logging) return;
    console.log(
      `BinaryHeap:\n\tLength: ${this.heap.length}\n\tHeap: ${this.heap}`,
    );
  }
}

module.exports = BinaryHeap;
