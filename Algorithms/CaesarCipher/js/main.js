const Alphabet = require("alphabet");

let caesarCipher = (str, num) => {
  num = num % 26;
  let lowerCaseString = str.toLowerCase();
  let alphabet = Alphabet.lower;
  let newString = "";

  for (var i = 0; i < lowerCaseString.length; i++) {
    let currentLetter = lowerCaseString[i];
    if (currentLetter === " ") {
      newString += currentLetter;
      continue;
    }
    let currentIndex = alphabet.indexOf(currentLetter);
    let newIndex = currentIndex + num;

    if (newIndex > 25) {
      newIndex = newIndex - 26;
    } else if (newIndex < 0) {
      newIndex = 26 + newIndex;
    }

    if (str[i] === str[i].toUpperCase()) {
      newString += alphabet[newIndex].toUpperCase();
    } else {
      newString += alphabet[newIndex];
    }
  }
  return newString;
};

const s = "Welcome Tyler";
const s1 = "To the";
const s2 = "Caesar Cipher";
const s3 = "Javascript tutorial";
const a = caesarCipher(s, 5);
const b = caesarCipher(s1, -16);
const c = caesarCipher(s2, 200);
const d = caesarCipher(s3, -900);

const encryptedMessage = `${a} ${b} ${c} ${d}`;

console.log(encryptedMessage);
