let hrn = (nTxt, mTxt) => {
  nTxt = nTxt.toLowerCase();
  mTxt = mTxt.toLowerCase();

  let nArr = nTxt.split(" ");
  let mArr = mTxt.split(" ");
  let mObj = {};

  let possibility = true;

  mArr.forEach((w) => {
    if (!mObj[w]) {
      mObj[w] = 0;
    }

    mObj[w]++;
  });

  nArr.forEach((w) => {
    if (mObj[w]) {
      mObj[w]--;
      if (mObj[w] < 0) {
        possibility = false;
      }
    } else {
      possibility = false;
    }
  });

  return possibility;
};

let note = "Let's meet at my place by five";
let magText =
  "Let's see if my dad will ever read the story of the five students who like to meet at the place by the creek";

hrn(note, magText);
