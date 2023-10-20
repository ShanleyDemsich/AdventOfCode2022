def create_tree_grid():
    """Turns the .txt tree grid file into a workable dictionary"""

    tree_row = []
    tree_grid = []

    # read the file line by line, character by character
    with open("trees.txt", "r") as f:
        for line in f:
            for char in line:
                # Add the tree heights within a row to a list
                tree_row.append(char)
            # Append a tree row to the tree grid dictionary for future lookups
            tree_grid.append(tree_row)
            tree_row = []  # reset the tree row for the next row of trees

    return tree_grid


def get_tree_grid():
    return create_tree_grid()


def count_visible_trees():
    """Counts the total number of visible trees in the tree grid.
    A visible tree is any that can be seen from the outside of the tree grid."""
    total_visible_trees = 0
    tree_grid = get_tree_grid()
    row = 1

    # All outer trees of the perimeter are visible
    total_visible_trees += 4 * len(tree_grid)

    # for _ in range(len(tree_grid_dict)):
    #     if row+1 == len(tree_grid_dict):
    #         break  # Reached the last row of tree grid
    #     above_row = tree_grid_dict[row]
    #     current_row = tree_grid_dict[row+1]
    #
    #     # check inner trees of the tree grid for visibility from outside grid
    #     for index, tree in enumerate(current_row[1:-2]):
    #         # Check if current tree is taller than trees above it
    #         if tree_is_visible(tree, above_row[index]):
    #             total_visible_trees += 1
    #     row += 1

    for row in tree_grid:
        for col in tree_grid[row]:
            current_tree_height = tree_grid[row][col]

            # Check if there is a taller tree to the current tree's left
            for left_col in range(col - 1, -1, -1):

            # Check if there is a taller tree to the current tree's right

            # Check if there is a taller tree above the current tree

            # Check if there is a taller tree below the current tree

    return total_visible_trees


def tree_is_visible(current_tree, comparison_tree):
    """Verifies if the current tree is taller than, able to be seen, the
    comparing tree"""

    return current_tree < comparison_tree


# Day 8, part 1
print(count_visible_trees())
