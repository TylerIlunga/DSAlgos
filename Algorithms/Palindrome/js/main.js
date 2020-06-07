const isPalindrome = (string) => {
  string = string.toLowerCase();
  let charactersArray = string.split('');
  let validCharacters = 'abcdefghijklmnopqrstuvwxyz'.split('');

  let lettersArray = [];
  charactersArray.forEach(char => {
    if (validCharacters.indexOf(char) > -1) {
      lettersArray.push(char);
    }
  })

  if (lettersArray.join('') === lettersArray.reverse().join('')) {
    return true
  } else {
    return false;
  }

}

isPalindrome("Madam I'm Adam");
isPalindrome("Race Car");
isPalindrome("Algorithms in Javascript");
