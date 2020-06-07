/**
 * Stack Data Structure
 */

class Stack {
  constructor(dataType = null) {
    this.isValidType(dataType);
    this.dataType = dataType;
    this.length = 0;
    this.stack = new Array();
    this.logging = true;
    this.printStack();
  }

  isValidType(object) {
    if (typeof object === "undefined" || typeof object === null) {
      throw new Error(`Invalid data type: ${typeof dataType}`);
    }
  }

  /**
   * isEmpty
   * @returns {boolean}
   */
  isEmpty() {
    return this.length === 0;
  }

  /**
   * evaluateObject
   * @param {Any} object
   */
  evaluateObject(object) {
    if (this.dataType !== typeof object) {
      throw new Error(`Object's data type [${typeof object}] is not invalid`);
    }
  }

  push(object) {
    this.evaluateObject(object);
    this.length++;
    this.stack.push(object);
    this.printStack();
  }

  /**
   * checkStackSize
   */
  checkStackSize() {
    if (this.length === 0) {
      throw new Error("Stack's length is 0. Can not pop an empty stack.");
    }
  }

  pop() {
    this.checkStackSize();
    this.length--;
    let obj = this.stack.pop();
    this.printStack();
    return obj;
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

  printStack() {
    if (!this.logging) return;
    console.log(
      `Stack Info:\n\tLength: ${this.length}\n\tStack: ${this.stack}`,
    );
  }
}

module.exports = Stack;

// let s = new Stack('number');
// s.push(5);
// s.push(25);
// s.push(55);
// s.pop();
// s.disableLogging();
// s.push(75);
// s.push(95);
// s.enableLogging();
// s.push(22);
// s.pop(95);
