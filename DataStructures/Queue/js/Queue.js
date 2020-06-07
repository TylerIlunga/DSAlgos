/**
 * Queue Data Structure
 */

class Queue {
  constructor(dataType = 'null') {
    this.isValidType(dataType);
    this.dataType = dataType;
    this.length = 0;
    this.queue = new Array();
    this.logging = true;
    this.printQueue();
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

  /**
   * enqueue
   * @param {Any} object
   */
  enqueue(object) {
    this.evaluateObject(object);
    this.length++;
    this.queue.push(object);
    this.printQueue();
  }

  /**
   * checkQueueSize
   */
  checkQueueSize() {
    if (this.length === 0) {
      throw new Error("Queue's length is 0. Can not dequeue an empty queue.");
    }
  }

  /**
   * dequeue
   */
  dequeue() {
    this.checkQueueSize();
    this.length--;
    let obj = this.queue.shift();
    this.printQueue();
    return obj;
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
   * printQueue
   */
  printQueue() {
    if (!this.logging) return;
    console.log(
      `Queue Info:\n\tLength: ${this.length}\n\tQueue: ${this.queue}`,
    );
  }
}

module.exports = Queue;

// let q = new Queue('number');
// q.enqueue(5);
// q.enqueue(25);
// q.enqueue(55);
// q.dequeue();
// q.disableLogging();
// q.enqueue(75);
// q.enqueue(95);
// q.enableLogging();
// q.enqueue(22);
// q.dequeue(95);
