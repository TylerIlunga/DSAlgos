const reverseWords = (string) => {
  let wordsArray = string.split(" ");
  var reverseWordsArray = [];

  wordsArray.forEach((word) => {
    var reversedWord = "";
    for (var i = word.length - 1; i >= 0; i--) {
      reversedWord += word[i];
    }
    reverseWordsArray.push(reversedWord);
  });

  return reverseWordsArray.join(" ");
};

console.log(reverseWords("Hello John"));
