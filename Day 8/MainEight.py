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
    total_visible_trees = 0
    tree_grid_dict = get_tree_grid()
    row = 1

    for _ in range(len(tree_grid_dict)):
        if row+1 == len(tree_grid_dict):
            break
        above_row = tree_grid_dict[row]
        current_row = tree_grid_dict[row+1]
        below_row = tree_grid_dict[row+2]

        # check each inner tree in the current row
        for index, tree in enumerate(current_row[1:-2]):
            if tree_is_visible(tree, above_row[index], below_row[index],
                               current_row[index-1], current_row[index+1]):
                total_visible_trees += 1
        row += 1
    return total_visible_trees


def tree_is_visible(current, above, below, left, right):
    """Verifies if the current tree is taller than, able to be seen, the
    trees left, right, above, or below"""

    return current > above or current > below or current > left or current > right


# Day 8, part 1
print(count_visible_trees())

