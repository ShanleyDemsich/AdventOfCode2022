import numpy as np


def create_tree_grid():
    """Turns the .txt tree grid file into a 2-D array"""
    tree_row = []
    tree_grid = []

    # read the file line by line, character by character
    with open("trees.txt", "r") as f:
        for line in f:
            for char in line:
                if char != '\n':
                    # Add the tree heights within a row to a list
                    tree_row.append(int(char))
            # Append a tree row to the tree grid 2-d array
            tree_grid.append(tree_row)
            tree_row = []  # reset the tree row for the next row of trees

    return tree_grid


def get_tree_grid():
    return create_tree_grid()


def create_tree_grid_masks(tree_grid, transposed_tree_grid):
    """Create masks of the visible trees from looking left, rihgt, up
    or down a matrix of tree heights"""
    left_to_right_tree_mask = []
    right_to_left_tree_mask = []
    top_to_bottom_tree_mask = []
    bottom_to_top_tree_mask = []

    for row in tree_grid:
        # Count visible trees from left to right
        left_to_right_tree_mask.append(visible_row_trees(row, row[0]))
        # Count visible trees from right to left
        right_to_left_tree_mask.append(
            visible_row_trees(list(reversed(row)), row[-1]))

    for row in transposed_tree_grid:
        # Count visible trees from looking down the grid
        top_to_bottom_tree_mask.append(visible_row_trees(row, row[0]))
        # Count visible trees from looking up the grid
        bottom_to_top_tree_mask.append(
            visible_row_trees(list(reversed(row)), row[-1]))

    return left_to_right_tree_mask, right_to_left_tree_mask, \
           top_to_bottom_tree_mask, bottom_to_top_tree_mask


def get_tree_grid_masks(tree_grid, transposed_tree_grid):
    return create_tree_grid_masks(tree_grid, transposed_tree_grid)


def combine_tree_grid_masks(left_right_mask, right_left_mask, top_bottom_mask,
                            bottom_top_mask):
    """Take boolean matrices and combine them into one by 'or'ing the
    corresponding boolean values in all the provided matrices."""
    left_right_mask = np.array(left_right_mask)
    right_left_mask = np.array(right_left_mask)
    top_bottom_mask = np.array(top_bottom_mask)
    bottom_top_mask = np.array(bottom_top_mask)

    combined_matrix = np.zeros((left_right_mask.shape[0], left_right_mask.shape[1]), dtype=bool)

    # Iterate over the rows and columns of the new matrix
    for i in range(combined_matrix.shape[0]):
        for j in range(combined_matrix.shape[1]):
            # Check if corresponding elements in the matrices are equal to 1
            if left_right_mask[i, j] or right_left_mask[i, j] or top_bottom_mask[i, j] or bottom_top_mask[i, j]:
                # Set the corresponding element in the new matrix
                combined_matrix[i, j] = True

    return combined_matrix


def visible_row_trees(row, max_height):
    """Create a mask of visible trees looking across a row"""
    mask_row = []

    for tree in row[1:-1]:
        if tree_is_visible(tree, max_height):
            # Set mask to true for visible tree
            mask_row.append(True)
            max_height = tree
        else:
            # Set mask to false for non-visible tree
            mask_row.append(False)
    return mask_row


def tree_is_visible(current_tree_height, comparison_tree_height):
    """Verifies if the current tree is taller than, able to be seen, the
    comparing tree"""
    return current_tree_height > comparison_tree_height


def count_visible_trees():
    """Counts the total number of visible trees in the tree grid.
    A visible tree is any that can be seen from the outside of the tree grid."""
    total_visible_trees = 0
    tree_grid = get_tree_grid()
    transposed_tree_grid = [[tree_grid[j][i] for j in range(len(tree_grid))] for
                            i in range(len(tree_grid[0]))]

    # All outer trees of the perimeter are visible
    total_visible_trees += 4 * len(tree_grid)

    # Retrieve the tree grid masks for each perspective of the grid
    left_right_mask, right_left_mask, top_bottom_mask, bottom_top_mask = get_tree_grid_masks(tree_grid, transposed_tree_grid)

    # Combine the matrices into one Boolean matrix
    combined_matrix = combine_tree_grid_masks(left_right_mask, right_left_mask,
                                              top_bottom_mask, bottom_top_mask)

    # Count number of visible trees from tree mask matrix
    for row in combined_matrix:
        for visible_tree in row:
            if visible_tree:
                total_visible_trees += 1

    return total_visible_trees


# Day 8, part 1
print(count_visible_trees())
