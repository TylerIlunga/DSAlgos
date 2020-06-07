String.prototype.sort = function () {
  return this.split("").sort().join("");
};

const anagrams = (word, words) => {
  return words.filter((x) => {
    return x.sort() === word.sort();
  });
};

const newAnagrams = (wordA, wordB) => {
  wordA = wordA.split("");
  wordB = wordB.split("");

  console.log(wordA);
  console.log(wordB);

  for (var i = 0; i < wordA.length; i++) {
    if (wordA[i].indexOf(wordB[i]) !== -1) {
      console.log(wordA[i].indexOf(wordB[i]));
    } else {
      console.log("not found!");
    }
  }
};

let output1 = anagrams("abba", ["aabb", "abcd", "bbaa", "dada"]);
let output2 = newAnagrams("abba", "bbaa");
console.log(output1);
console.log(output2);
