const reverseString = string => {
  string = string.split(' ');

  const reverseStringArray = [];

  string.forEach(word => {
    let reversedString = '';
    for (var i = word.length - 1; i >= 0; i--) {
      reversedString += word[i];
    }
    reverseStringArray.push(reversedString);
  });

  return reverseStringArray.join(' ');
};

const string = 'Good Morning Tyler';
const result = reverseString(string);
console.log(result);
