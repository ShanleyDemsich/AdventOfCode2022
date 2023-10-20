def create_tree_grid():
    """Turns the .txt tree grid file into a 2-D array"""

    tree_row = []
    tree_grid = []

    # read the file line by line, character by character
    with open("trees.txt", "r") as f:
        for line in f:
            for char in line:
                # Add the tree heights within a row to a list
                tree_row.append(int(char))
            # Append a tree row to the tree grid 2-d array
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
        # Count visible trees from left to right
        total_visible_trees += visible_row_trees(row, row[0])
        # Count visible trees from right to left
        total_visible_trees += visible_row_trees(reversed(row), row[-1])
        # Count visible trees from looking down the grid

        # Count visible trees from looking up the grid

    return total_visible_trees


def visible_row_trees(row, max_height):
    """Count the number of visible trees looking across a row"""
    visible_trees = 0

    for tree in row[1:-1]:
        if tree_is_visible(tree, max_height):
            visible_trees += 1
            max_height = tree

    return visible_trees

def visible_col_trees(row, max_height):
    for tree in row[1:-1]:




def tree_is_visible(current_tree_height, comparison_tree_height):
    """Verifies if the current tree is taller than, able to be seen, the
    comparing tree"""

    return current_tree_height > comparison_tree_height


# Day 8, part 1
print(count_visible_trees())
