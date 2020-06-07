// Constant time (O(1)) insertion
// Constant time (O(1)) lookup w/ no collisions else O(N)

class HashTable {
  constructor(size) {
    this.buckets = Array(size);
    this.numberOfBuckets = this.buckets.length;
  }
}

class HashNode {
  constructor(key, value, next) {
    this.key = key;
    this.value = value;
    this.next = next ? next : null;
  }
}

HashTable.prototype.hash = function (key) {
  let total = 0;
  for (var i = 0; i < key.length; i++) {
    total += key.charCodeAt(i);
  }
  let bucket = total % this.numberOfBuckets;
  return bucket;
};

HashTable.prototype.insert = function (key, value) {
  var index = this.hash(key);
  //console.log(`Index for ${key}: [${index}]`);
  if (!this.buckets[index]) {
    this.buckets[index] = new HashNode(key, value);
  } else if (this.buckets[index].key === key) {
    this.buckets[index].value = value;
  } else {
    let currentNode = this.buckets[index];
    while (currentNode.next) {
      if (currentNode.next.key === key) {
        currentNode.next.value = value;
        return;
      }
      currentNode = currentNode.next;
    }
    currentNode.next = new HashNode(key, value);
  }
};

HashTable.prototype.get = function (key) {
  let index = this.hash(key);
  if (!this.buckets[index]) {
    return null;
  } else {
    let currentNode = this.buckets[index];
    while (currentNode) {
      if (currentNode.key === key) {
        return currentNode.value;
      }
      currentNode = currentNode.next;
    }
    return null;
  }
};

HashTable.prototype.retrieveAll = function () {
  let allNodes = [];
  for (var i = 0; i < this.numberOfBuckets; i++) {
    let currentNode = this.buckets[i];
    while (currentNode) {
      allNodes.push(currentNode);
      currentNode = currentNode.next;
    }
  }

  return allNodes;
};

let ht = new HashTable(30);
let h = ht.hash("tyler");
console.log(h);
ht.insert("Tyler", "tilunga@lion.lmu.edu");
ht.insert("Kyle", "ky@gmail.com");
ht.insert("Dean", "dean@gmail.com");
ht.insert("Dane", "dane@gmail.com");
ht.insert("Kyle", "kyle@gmail.com");
ht.insert("Dean", "dean123@gmail.com");
console.log(ht.buckets);
console.log(ht.get("Kyle"));
console.log(ht.retrieveAll());
