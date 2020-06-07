class LinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
  }
}

class Node {
  constructor(value, next, prev) {
    this.value = value;
    this.next = next;
    this.prev = prev;
  }
}

LinkedList.prototype.addNewHead = function (value) {
  let newNode = new Node(value, this.head, null);

  if (this.head) {
    this.head.prev = newNode;
  } else {
    this.tail = newNode;
  }

  this.head = newNode;
};

LinkedList.prototype.addNewTail = function (value) {
  let newNode = new Node(value, null, this.tail);

  if (this.tail) {
    this.tail.next = newNode;
  } else {
    this.head = newNode;
  }

  this.tail = newNode;
};

LinkedList.prototype.removedHead = function () {
  if (!this.head) {
    return null;
  }

  let val = this.head.value;

  this.head = this.head.next;

  if (this.head) {
    this.head.prev = null;
  } else {
    this.tail = null;
  }

  return val;
};

LinkedList.prototype.removeTail = function () {
  if (!this.tail) {
    return null;
  }

  let val = this.tail.value;
  this.tail = this.tail.prev;

  if (this.tail) {
    this.tail.next = null;
  } else {
    //if list is empty after removing tail
    this.head = null;
  }

  return val;
};

LinkedList.prototype.query = function (qValue) {
  let currentNode = this.head;

  while (currentNode) {
    if (currentNode.value === qValue) {
      return `${currentNode.value} is in the list`;
    }
    currentNode = currentNode.next;
  }

  return `${qValue} is not in the list`;
};

LinkedList.prototype.indexOf = function (value) {
  let indexes = [];
  let currentIndex = 0;
  let currentNode = this.head;

  while (currentNode) {
    if (currentNode.value == value) {
      indexes.push(currentIndex);
    }

    currentNode = currentNode.next;
    currentIndex++;
  }

  return indexes;
};

let ll = new LinkedList();

ll.addNewHead(100);
ll.addNewHead(100);
ll.addNewHead(200);
ll.addNewHead(300);
ll.addNewTail(50);
ll.addNewTail(25);
ll.addNewTail(10);
console.log(`Head removed: ${ll.removedHead()}`);
console.log(`Tail removed: ${ll.removeTail()}`);
console.log(ll.query(200));
console.log(ll.query(4000));
console.log(`Found in indexes: [${ll.indexOf(100)}]`);

console.log(ll);
console.log(ll.head);
console.log(ll.head.next);
console.log(ll.tail);
console.log(ll.tail.prev);
