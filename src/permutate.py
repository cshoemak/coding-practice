class PermutateStrings():

    @classmethod
    def permutate(cls, passed_string):
        for permutation in cls._permutate(passed_string):
            print(permutation)

    @classmethod
    def _permutate(cls, passed_string):
        my_character = passed_string[0]
        if len(passed_string) < 2:
            return [my_character]
        else:
            my_strings = []
            string_list = cls._permutate(passed_string[1:])
            for string_item in string_list:
                index = 0
                while index <= len(string_item):
                    my_strings.append(string_item[:index] + my_character + string_item[index:])
                    index += 1
            return my_strings
