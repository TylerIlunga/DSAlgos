const Alphabet = require('alphabet');

const cc = (s, n) => {
  n = n % 26;
  let lowerCaseStr = s.toLowerCase();
  let a = Alphabet.lower;
  let newStr = '';

  for (var i = 0; i < lowerCaseStr.length; i++) {
    let currentStr = lowerCaseStr[i];
    if (currentStr === ' ') {
      newStr += currentStr;
      continue;
    }
    let currentIndex = a.indexOf(currentStr);
    let newIndex = currentIndex + n;

    if (newIndex > 25) {
      newIndex = newIndex - 26;
    } else if (newIndex < 0) {
      newIndex = newIndex + 26;
    }
    if (s[i] === s[i].toUpperCase()) {
      newStr += a[newIndex].toUpperCase();
    } else {
      newStr += a[newIndex];
    }
  }
  return newStr;
}

console.log(cc('Zoo Keeper', 2));
