const makeAPalindrome = (string) => {
  string = string.split("");

  for (var i = 0; i < string.length / 2; i++) {
    if (string[i] !== string[string.length - 1 - i]) {
      string[i] = string[string.length - 1 - i];
    }
  }

  return string.join("");
};

console.log(makeAPalindrome("cara"));
