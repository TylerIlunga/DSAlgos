const alphabet = require("alphabet");

let isPalindrome = (s) => {
  s = s.toLowerCase();
  let charArr = s.split("");
  let validChars = alphabet.lower;
  let lettersArr = [];

  charArr.forEach((c) => {
    if (validChars.indexOf(c) > -1) {
      lettersArr.push(c);
    }
  });

  if (lettersArr.join("") === lettersArr.reverse().join("")) {
    return true;
  } else {
    return false;
  }
};

isPalindrome("RACE CAR");
isPalindrome("Superstar");
isPalindrome("madam I'm Adam");
