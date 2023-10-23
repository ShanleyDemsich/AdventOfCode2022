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
    left_to_right_tree_mask = np.array(left_to_right_tree_mask)

    # rotate the tree grid 90 degrees counter-clockwise
    tree_grid = np.rot90(tree_grid)

    for row in tree_grid:
        # Label visible trees from top to bottom
        top_to_bottom_tree_mask.append(visible_row_trees(row))
    # Rotate the mask to original position
    top_to_bottom_tree_mask = np.array(top_to_bottom_tree_mask)
    top_to_bottom_tree_mask = np.rot90(top_to_bottom_tree_mask, 3)

    # rotate the tree grid 90 degrees counter-clockwise
    tree_grid = np.rot90(tree_grid)

    for row in tree_grid:
        # Label visible trees from right to left
        right_to_left_tree_mask.append(visible_row_trees(row))
    # Rotate the mask to original position
    right_to_left_tree_mask = np.array(right_to_left_tree_mask)
    right_to_left_tree_mask = np.rot90(right_to_left_tree_mask, 2)

    # rotate the tree grid 90 degrees counter-clockwise
    tree_grid = np.rot90(tree_grid)

    for row in tree_grid:
        # Label visible trees from bottom to top
        bottom_to_top_tree_mask.append(visible_row_trees(row))
    # Rotate the mask to original position
    bottom_to_top_tree_mask = np.array(bottom_to_top_tree_mask)
    bottom_to_top_tree_mask = np.rot90(bottom_to_top_tree_mask, 1)

    return left_to_right_tree_mask, top_to_bottom_tree_mask, right_to_left_tree_mask, bottom_to_top_tree_mask


def combine_tree_grid_masks(left_right_mask, right_left_mask, top_bottom_mask,
                            bottom_top_mask):
    """Take boolean matrices and combine them into one by 'or'ing the
    corresponding boolean values in all the provided matrices."""
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
    left_right_mask, top_bottom_mask, right_left_mask, bottom_top_mask = create_tree_grid_mask(tree_grid)

    # Combine the matrices into one Boolean matrix
    combined_matrix = combine_tree_grid_masks(left_right_mask, top_bottom_mask, right_left_mask, bottom_top_mask)

    # Count total visible trees
    combined_matrix_sum = np.sum(combined_matrix)

    total_visible_trees += combined_matrix_sum

    return total_visible_trees


def calculate_viewing_distances(tree_grid):
    """Calculate each tree's viewing distance, how many trees outward in each
    direction it can see multiplied together. Visible trees are up to and
    including the next most tree that hides trees beyond the current tree's
    view (must be equal height or greater to current tree)."""
    tree_grid = np.array(tree_grid)
    viewing_distances = []

    for row_index, row in enumerate(tree_grid):
        row_viewing_distances = []
        for col_index, tree in enumerate(row):
            current_row_index = row_index
            current_col_index = col_index

            # Calculate visible trees to the left of current tree
            left_view = calculate_left_viewing_distance(tree_grid, current_row_index, current_col_index - 1, tree)

            # Calculate visible trees to the right of current tree
            right_view = calculate_right_viewing_distance(tree_grid, current_row_index, current_col_index + 1, tree)

            # Calculate visible trees from above
            top_view = calculate_top_viewing_distance(tree_grid, current_row_index - 1, current_col_index, tree)

            # Calculate visible trees below
            bottom_view = calculate_bottom_viewing_distance(tree_grid, current_row_index + 1, current_col_index, tree)

            # Only multiply non-zero values
            viewing_distance = non_zero_multiplication([left_view, right_view, top_view, bottom_view])
            row_viewing_distances.append(viewing_distance)

        viewing_distances.append(row_viewing_distances)

    return np.array(viewing_distances)


def calculate_left_viewing_distance(tree_grid, row_index, col_index, tree):
    """Count how many trees can be viewed to the left."""
    left_viewing_distance = 0

    while col_index >= 0:
        comp = tree_grid[row_index, col_index]
        if comp >= tree:
            left_viewing_distance += 1
            break  # Reached last visible tree in this direction
        left_viewing_distance += 1
        col_index -= 1

    return left_viewing_distance


def calculate_right_viewing_distance(tree_grid, row_index, col_index, tree):
    """Count how many trees can be viewed to the right."""
    right_viewing_distance = 0

    while col_index < len(tree_grid):
        if tree_grid[row_index, col_index] >= tree:
            right_viewing_distance += 1
            break  # Reached last visible tree in this direction
        right_viewing_distance += 1
        col_index += 1

    return right_viewing_distance


def calculate_top_viewing_distance(tree_grid, row_index, col_index, tree):
    """Count how many trees can be viewed above the tree."""
    top_viewing_distance = 0

    while row_index >= 0:
        if tree_grid[row_index, col_index] >= tree:
            top_viewing_distance += 1
            break  # Reached last visible tree in this direction
        top_viewing_distance += 1
        row_index -= 1

    return top_viewing_distance


def calculate_bottom_viewing_distance(tree_grid, row_index, col_index, tree):
    """Count how many trees can be viewed below the tree."""
    bottom_viewing_distance = 0

    while row_index < len(tree_grid):
        if tree_grid[row_index, col_index] >= tree:
            bottom_viewing_distance += 1
            break  # Reached last visible tree in this direction
        bottom_viewing_distance += 1
        row_index += 1

    return bottom_viewing_distance


def non_zero_multiplication(integers):
    """Given a list of integers only multiply non-zero integers together."""
    product = 1
    for integer in integers:
        if integer != 0:
            product *= integer
    return product


def find_best_viewing_distance(file):
    """Find the greatest viewing distance in the tree grid."""
    tree_grid = get_tree_grid(file)

    viewing_distances = calculate_viewing_distances(tree_grid)

    best_viewing_distance = np.amax(viewing_distances)

    return best_viewing_distance


# Day 8, part 1
# print(count_visible_trees("trees.txt"))


# Day 8, part 2
print(find_best_viewing_distance("trees.txt"))
