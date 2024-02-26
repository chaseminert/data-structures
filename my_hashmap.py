from typing import TypeVar
from collections.abc import Hashable


def main():
    hashmap = MyHashMap()
    hashmap["Hello"] = "World"
    hashmap["Chase"] = "IS awesome"
    hashmap[1] = [1, 2, 3]
    hashmap["test"] = "testing"
    hashmap["hi"] = "idi"
    hashmap["test"] = "new val for test"
    del hashmap["Chase"]
    item = hashmap["test"]
    print(hashmap, end="\n\n")
    #for key in hashmap:
        #print(key)
    for key, value in hashmap.items():
        print(f"key: {key} | value: {value}")



KEY = TypeVar("KEY", bound=Hashable)
VALUE = TypeVar("VALUE")

STARTING_MAX_SIZE = 2
LOAD_FACTOR = 0.75


class MyHashMap:
    def __init__(self):
        self._current = 0  # only used for iterating
        self._list: list[list[tuple[KEY, VALUE]]] = [[] for _ in range(STARTING_MAX_SIZE)]
        self._current_max_size: int = STARTING_MAX_SIZE
        self._current_size: int = 0

    def _put(self, new_key, new_val) -> None:
        hash_key: int = self._hash(new_key)
        old: list[tuple[KEY, KEY]] = self._list[hash_key]

        key_already_exists = False
        index_of_key = -1
        for index, (key, val) in enumerate(old):
            if key == new_key:
                key_already_exists = True
                index_of_key = index
                break

        if key_already_exists:
            index: int = index_of_key
            self._list[hash_key][index] = (new_key, new_val)
        else:
            self._list[hash_key].append((new_key, new_val))
        if not old:  # if old is empty
            self._current_size += 1
        if (self._current_size / self._current_max_size) > LOAD_FACTOR:
            self._rehash_all()

    def _get(self, key) -> VALUE:
        list_index = None

        hash_key: int = self._hash(key)
        items_list: list[tuple[KEY, VALUE]] = self._list[hash_key]
        for og_key, val in items_list:
            if og_key == key:
                return val
        raise KeyError(f"key {key} not found")

    def _remove(self, key) -> None:
        hash_key: int = self._hash(key)
        sublist: list[tuple[KEY, VALUE]] = self._list[hash_key]
        for index, (og_key, val) in enumerate(sublist):
            if key == og_key:
                sublist.pop(index)
                return
        raise KeyError(f"Key {key} not in HashMap")

    def _rehash_all(self) -> None:
        self._current_max_size *= 2
        new_list: list[list[tuple[KEY, VALUE]]] = [[] for _ in range(self._current_max_size)]
        for value_list in self._list:  # 5439543 -> [("hello", "word"), ("chase", "is cool")]
            if not value_list:
                continue
            for key_val_tuple in value_list:
                key, val = key_val_tuple
                new_hash: int = self._hash(key)
                new_list[new_hash].append(key_val_tuple)
        self._list = new_list

    def _hash(self, val) -> int:
        return hash(val) % self._current_max_size

    def _key_set(self) -> list[KEY]:
        key_set: list["KEY"] = []
        for key_list in self._list:
            if not key_list:
                continue
            for key, val in key_list:
                key_set.append(key)
        return key_set

    def _debug_print(self) -> None:
        string = ""
        for index, sublist in enumerate(self._list):
            if not sublist:
                continue
            string += f"hash: {index} | {sublist}\n"
        print(string)

    def __str__(self):
        string = ""
        for sublist in self._list:
            if sublist == [[]]:
                continue
            for og_key, value in sublist:
                string += f"{og_key} -> {value}\n"
        return string

    def __setitem__(self, key: KEY, value: VALUE):
        return self._put(key, value)

    def __getitem__(self, key: KEY) -> VALUE:
        return self._get(key)

    def __iter__(self) -> KEY:
        for key in self._key_set():
            yield key

    def items(self) -> tuple[KEY, VALUE]:
        for key_list in self._list:
            if not key_list:
                continue
            for key_val_pair in key_list:
                yield key_val_pair

    def __delitem__(self, key: KEY) -> None:
        self._remove(key)


if __name__ == "__main__":
    main()
