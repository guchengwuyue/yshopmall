'use strict';

const assert = require('assert');
const stringToParts = require('../lib/stringToParts');

describe('stringToParts', function() {
  it('handles brackets for numbers', function() {
    assert.deepEqual(stringToParts('list[0].name'), ['list', '0', 'name']);
    assert.deepEqual(stringToParts('list[0][1].name'), ['list', '0', '1', 'name']);
  });

  it('handles dot notation', function() {
    assert.deepEqual(stringToParts('a.b.c'), ['a', 'b', 'c']);
    assert.deepEqual(stringToParts('a..b.d'), ['a', 'b', 'd']);
  });

  it('throws for invalid numbers in square brackets', function() {
    assert.throws(() => stringToParts('foo[1mystring]'), /1mystring/);
  });

  it('handles empty string', function() {
    assert.deepEqual(stringToParts(''), ['']);
  });

  it('handles trailing dot', function() {
    assert.deepEqual(stringToParts('a.b.'), ['a', 'b', '']);
  });
});