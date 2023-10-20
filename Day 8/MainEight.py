import numpy as np


def create_tree_grid(file):
    """Turns the .txt tree grid file into a 2-D array"""
    tree_row = []
    tree_grid = []

    # read the file line by line, character by character
    with open(file, "r") as f:
        for line in f:
            for char in line:
                if char != '\n':
                    # Add the tree heights within a row to a list
                    tree_row.append(int(char))
            # Append a tree row to the tree grid 2-d array
            tree_grid.append(tree_row)
            tree_row = []  # reset the tree row for the next row of trees

    return tree_grid


def get_tree_grid(file):
    return create_tree_grid(file)


def create_tree_grid_mask(tree_grid):
    """Create masks of the visible trees from looking left, right, up
    or down a matrix of tree heights"""
    tree_grid = np.array(tree_grid)
    left_to_right_tree_mask = []
    right_to_left_tree_mask = []
    top_to_bottom_tree_mask = []
    bottom_to_top_tree_mask = []

    for row in tree_grid:
        # Label visible trees from left to right
        left_to_right_tree_mask.append(visible_row_trees(row))

    # rotate the tree grid 90 degrees clockwise
    tree_grid = np.rot90(tree_grid)

    for row in tree_grid:
        # Label visible trees from bottom to top
        bottom_to_top_tree_mask.append(visible_row_trees(row))

    # rotate the tree grid 90 degrees clockwise
    tree_grid = np.rot90(tree_grid)

    for row in tree_grid:
        # Label visible trees from right to left
        right_to_left_tree_mask.append(visible_row_trees(row))

    # rotate the tree grid 90 degrees clockwise
    tree_grid = np.rot90(tree_grid)

    for row in tree_grid:
        # Label visible trees from top to bottom
        top_to_bottom_tree_mask.append(visible_row_trees(row))

    return left_to_right_tree_mask, right_to_left_tree_mask, \
           top_to_bottom_tree_mask, bottom_to_top_tree_mask


def combine_tree_grid_masks(left_right_mask, right_left_mask, top_bottom_mask,
                            bottom_top_mask):
    """Take boolean matrices and combine them into one by 'or'ing the
    corresponding boolean values in all the provided matrices."""
    left_right_mask = np.array(left_right_mask)
    right_left_mask = np.array(right_left_mask)
    top_bottom_mask = np.array(top_bottom_mask)
    bottom_top_mask = np.array(bottom_top_mask)

    combined_matrix = left_right_mask | right_left_mask | top_bottom_mask | bottom_top_mask

    return combined_matrix


def visible_row_trees(row):
    """Create a mask of visible trees looking across a row"""
    mask_row = [True]
    max_height = row[0]

    for tree in row[1:]:
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


def count_visible_trees(file):
    """Counts the total number of visible trees in the tree grid.
    A visible tree is any that can be seen from the outside of the tree grid."""
    total_visible_trees = 0
    tree_grid = get_tree_grid(file)

    # Retrieve the tree grid masks for each perspective of the grid
    left_right_mask, right_left_mask, top_bottom_mask, bottom_top_mask = create_tree_grid_mask(tree_grid)

    # Combine the matrices into one Boolean matrix and count total visible trees
    combined_matrix_sum = np.sum(combine_tree_grid_masks(left_right_mask, right_left_mask, top_bottom_mask, bottom_top_mask))

    total_visible_trees += combined_matrix_sum

    return total_visible_trees


# Day 8, part 1
print(count_visible_trees("trees.txt"))
