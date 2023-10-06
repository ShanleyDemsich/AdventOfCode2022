def create_tree_grid():
    """Turns the .txt tree grid file into a workable dictionary"""

    tree_row = []
    tree_grid_dict = {}
    row = 1

    # read the file line by line, character by character
    with open("trees.txt", "r") as f:
        for line in f:
            for char in line:
                # Add the tree heights within a row to a list
                tree_row.append(char)
            # Append a tree row to the tree grid dictionary for future lookups
            tree_grid_dict[row] = tree_row
            tree_row = []  # reset the tree row for the next row of trees
            row += 1  # increment the row counter
    return tree_grid_dict


def get_tree_grid():
    return create_tree_grid()


def count_visible_trees():
    """Counts the total number of visible trees in the tree grid.
    A visible tree is any that can be seen from above, below, to the left
    or to the right of the tree. """

    tree_grid_dict = get_tree_grid()
    above_row = tree_grid_dict[1]
    current_row = tree_grid_dict[2]
    below_row = tree_grid_dict[3]


def is_tree_visible(current, above, below, left, right):
    """Verifies if the current tree is taller than, able to be seen, the
    trees left, right, above, or below"""

    return current > above and current > below \
           and current > left and current > right


# Day 8, part 1
print(create_tree_grid())
