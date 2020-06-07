const palindrome = (string) => {
  let charArray = string.split(" ");

  for (var i = 0; i < charArray.length / 2; i++) {
    if (charArray[i] !== charArray[charArray.length - 1 - i]) {
      charArray[i] = charArray[charArray - 1 - i];
    }
  }

  return charArray.join("");
};

console.log(palindrome("aba"));
