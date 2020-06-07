//Linked Lists:
//A list of elements, called nodes(JS Objects), that are linked together in a single file line
//**Only needs to know about the HEAD & TAIL node to function property**
//HEAD POINTER and TAIL POINTER keeps the ref

//NODES: { value: N/A, next: nextNode, prev: prevNode }

//Two types

//Singly Linked:
//Each node only has ref to the next node(the one after)

//Doubly Linked:
//Each node has ref to the next node and the previous node

//O (1)


class LinkedList {
  constructor() {
    //no nodes added yet
    //nothing to point to
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
LinkedList.prototype.addNewHead = function(value) {
  //new head, nextNode(oldHead), no previous Head
  let newNode = new Node(value, this.head, null);

  //if there are already nodes because this.head is only set with at least one node
  if (this.head) {
    //set prev HEAD pointer to our new head node
    this.head.prev = newNode

  //if LinkedList is empty
  } else {
    //set tail pointer equal to our new node since it's our only node added to the list
    //TAIL pointer set and HEAD has to be set no matter what
    this.tail = newNode
  }

  //newNode has to be the HEAD no matter what
  this.head = newNode
};

//Adding to the TAIL
LinkedList.prototype.addNewTail = function(value) {
  let newNode = new Node(value, null, this.tail)

  if (this.tail) {

    this.tail.next = newNode

  } else {

    this.head = newNode

  }

  this.tail = newNode
}

//Removing HEAD
LinkedList.prototype.removedHead = function () {
  if (!this.head) {
    return null;
  }

  let val = this.head.value;

  //set head to next value that will become the head
  this.head = this.head.next;

  if (this.head) {
    this.head.prev = null;
  } else {
    this.tail = null;
  }

  return val;
};

//Removing TAIL

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

//QUERY LIST
//O (n)
LinkedList.prototype.query = function (qValue) {
  let currentNode = this.head;

  while (currentNode) {
    if (currentNode.value === qValue) {
      return `${currentNode.value} is in the list`
    }
    currentNode = currentNode.next;
  }

  return `${qValue} is not in the list`;

};

//Index query

LinkedList.prototype.indexOf = function (value) {
  let indexes = []
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

ll.addNewHead(100)
ll.addNewHead(100)
ll.addNewHead(200)
ll.addNewHead(300)
ll.addNewTail(50)
ll.addNewTail(25)
ll.addNewTail(10)
console.log(`Head removed: ${ll.removedHead()}`);
console.log(`Tail removed: ${ll.removeTail()}`);
console.log(ll.query(200));
console.log(ll.query(4000));
console.log(`Found in indexes: [${ll.indexOf(100)}]`);

//[Circular] = nodes are starting to reference each other in a circle(head -> next , next -> Head)
console.log(ll);
console.log(ll.head);
console.log(ll.head.next);
console.log(ll.tail);
console.log(ll.tail.prev);
