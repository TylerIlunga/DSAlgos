const anagrams = (word1, word2) => {
  return word1.split("").sort().join("") === word2.split("").sort().join("");
};

let output = anagrams("abvba", "baba");
console.log(output);
