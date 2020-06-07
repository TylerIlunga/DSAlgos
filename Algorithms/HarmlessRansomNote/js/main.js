//Note made from words cut off of a magazine
//O(n + m) || O(n)
const harmlessRansomNote = (noteText, magazineText) => {
  let noteArray = noteText.split(" ");
  let magazineArray = magazineText.split(" ");
  let magazineObject = {};

  magazineArray.forEach((word) => {
    if (!magazineObject[word]) {
      magazineObject[word] = 0;
    }

    magazineObject[word]++;
  });

  let noteIsPossible = true;

  noteArray.forEach((word) => {
    if (magazineObject[word]) {
      magazineObject[word]--;
      if (magazineObject[word] < 0) {
        noteIsPossible = false;
      }
    } else {
      noteIsPossible = false;
    }
  });

  return noteIsPossible;
};

let magTxt =
  "there was an amazing breakthrough in the field of computer science today";
let noteText = "science in computer science is amazing";

harmlessRansomNote(noteText, magTxt);
