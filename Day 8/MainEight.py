def create_tree_grid():
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


# Day 8, part 1
print(create_tree_grid())
