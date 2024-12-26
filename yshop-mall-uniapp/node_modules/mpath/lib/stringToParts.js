'use strict';

module.exports = function stringToParts(str) {
  const result = [];

  let curPropertyName = '';
  let inSquareBrackets = false;
  for (let i = 0; i < str.length; ++i) {
    if (inSquareBrackets && !/\d/.test(str[i]) && str[i] !== ']') {
      throw new Error('Can only use numbers in square bracket path notation, got ' +
        'character "' + str[i] + '" in path "' + str + '"');
    }

    if (str[i] === '.' || str[i] === '[' || str[i] === ']') {
      if (str[i] === '[') {
        inSquareBrackets = true;
      } else if (str[i] === ']') {
        inSquareBrackets = false;
      }
      if (curPropertyName.length > 0) {
        result.push(curPropertyName);
      }
      curPropertyName = '';
    } else {
      curPropertyName += str[i];
    }
  }
  result.push(curPropertyName);

  return result;
};