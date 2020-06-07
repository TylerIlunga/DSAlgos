//memoized O(n)
//cache creates memory that can be referenced
const fibMemo = (index, cache) => {
  cache = cache || [];
  if (cache[index]) {
    return cache[index]
  } else {
    if (index < 3) {
      return 1
    } else {
      cache[index] = fibMemo(index - 1, cache) + fibMemo(index - 2, cache)
    }
  }

  return cache[index];
}

let fibMemoResult = fibMemo(1000);
console.log(fibMemoResult);

//runtime: O(2^N) very inefficient
const fibonacci = (position) => {
  if (position < 3) return 1
  else return fibonacci(position - 1) + fibonacci(position - 2);
}

let result = fibonacci(40);
console.log(result);
