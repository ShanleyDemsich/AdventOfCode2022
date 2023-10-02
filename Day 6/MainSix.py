import string


def find_data_marker():
    marker = 0  # goal of 4
    marker_index = 0  # Marks end of marker

    alpha_dict = create_alpha_dict()

    # read file character by character
    with open("datastream.txt", "r") as f:
        # while there are still characters to read
        while True:
            char = f.read(1)
            if char == "":
                break

            # increment the characters count
            marker_index += 1
            current_char = alpha_dict.get(char)

            # if character was seen in any of the previous 3 chars
            if current_char > 0:
                # reset the dictionary and marker length counter
                alpha_dict = create_alpha_dict()
                marker = 0

            # Update dictionary value for char
            alpha_dict[char] += 1
            # increment the marker length counter
            marker += 1

            if marker == 4:
                return marker_index


def create_alpha_dict():
    # Create a dictionary with lowercase letters as keys
    # and values initialized to 0
    return dict.fromkeys(string.ascii_lowercase, 0)


# Day 6, part 1:
print(find_data_marker())

# Day 6, part 2: