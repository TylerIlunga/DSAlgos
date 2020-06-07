const harmlessRansomNote = (noteText, magazineText) => {
  let noteArray = noteText.split(" "),
    magazineArray = magazineText.split(" "),
    magazineObject = {},
    notePossibility = true;

  const checkMagazineArray = (word) => {
    if (!magazineObject[word]) {
      magazineObject[word] = 0;
    }
    magazineObject[word]++;
  };

  magazineArray.forEach(checkMagazineArray);

  const checkNoteArray = (word) => {
    if (magazineObject[word]) {
      magazineObject[word]--;
      if (magazineObject[word] < 0) {
        notePossibility = false;
      }
    } else {
      notePossibility = false;
    }
  };

  noteArray.forEach(checkNoteArray);

  return notePossibility;
};

let magTxt =
  "there was an amazing breakthrough in the field of computer science today";
let noteText = "science in computer was amazing";

console.log(harmlessRansomNote(noteText, magTxt));

const appendToText = (text) => {
  let textArray = text.split(" ");

  let counter = 0;

  while (counter < 2) {
    textArray.push(text);
    counter++;
  }

  return textArray.join("");
};

const string = "ho";
const output = textAppending(string);
console.log(output);
