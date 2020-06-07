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

//Adding a NODE to the HEAD
LinkedList.prototype.insertHead = function (value) {
  let newNode = new Node(value, this.head, null);

  if (this.head) {
    this.head.prev = newNode;
  } else {
    this.tail = newNode;
  }

  this.head = newNode;
};

//Adding to the TAIL
LinkedList.prototype.insertTail = function (value) {
  let newNode = new Node(value, null, this.tail);

  if (this.tail) {
    this.tail.next = newNode;
  } else {
    this.head = newNode;
  }

  this.tail = newNode;
};

//Removing HEAD

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

//Removing TAIL
LinkedList.prototype.removedTail = function () {
  if (!this.tail) {
    return null;
  }

  let val = this.tail.value;
  this.tail = this.tail.prev;

  if (this.tail) {
    this.tail.next = null;
  } else {
    this.head = null;
  }

  return val;
};
//QUERY LIST
LinkedList.prototype.query = function (value) {
  let currentNode = this.head;

  while (currentNode) {
    if (currentNode.value === value) {
      return `${value} is on the list.`;
    }

    currentNode = currentNode.next;
  }

  return `${value} is not in the list.`;
};

//Index query
LinkedList.prototype.index = function (value) {
  let indexes = [];
  let currentIndex = 0;
  let currentNode = this.head;

  while (currentNode) {
    if (currentNode.value === value) {
      indexes.push(currentIndex);
    }
    currentNode = currentNode.next;
    currentIndex++;
  }

  return `${value} is located in index(es): [${indexes}]`;
};

let family = new LinkedList();
family.insertHead("Chal");
family.insertHead("Kyle");
family.insertHead("Tyler");
family.insertHead("Pascal");
family.insertHead("Dominique");
family.insertTail("Danielle");
family.insertTail("Cairo");
family.insertTail("Test");
family.removedHead();
family.removedTail();
console.log(family.query("Tyler"));
console.log(family.query("Herve"));
console.log(family.index("Tyler"));
console.log(family);
