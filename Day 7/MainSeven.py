def read_file_by_line(file):
    """Read in a file line by line."""
    lines = []
    with open(file, "r") as f:
        for line in f:
            lines.append(line.strip())
    return lines


def is_command_line(line):
    """Returns True if the current line is a command."""
    return line[0] is "$"


def get_command(line):
    """Returns the command."""

    if line[5:7].equals(".."):
        return "out"
    elif line[5].equals("/"):
        return "switch"
    else:
        return line[5]


def create_directory_dictionary(lines):
    directory_dictionary = dict()

    directory_level = 0

    for line in lines:
        if is_command_line(line):
            command = get_command(line)
            if command.equals("in"):
                directory_level += 1
            elif command.equals("out"):
                directory_level -= 1
            else:
                directory_dictionary[command]


def size_is_at_most_100000(directory_size):
    return directory_size <= 100000


def find_total_directory_size(file):
    """Calculate the total size of all directories with a total size of at
    most 100000."""
    sum_directory_sizes = 0

    lines = read_file_by_line(file)

    return sum_directory_sizes


# Day 7, part 1:
print(find_total_large_directory_size("directories.txt"))

# Day 7, part 2:
print()