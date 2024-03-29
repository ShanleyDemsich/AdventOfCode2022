import unittest

from MainEight import *


class MyTestCase(unittest.TestCase):
    def test_create_tree_grid(self):
        actual = create_tree_grid("testTrees.txt")
        expected = [[0, 1, 2, 8],
                    [1, 1, 3, 2],
                    [5, 1, 4, 6],
                    [2, 2, 0, 0]]

        self.assertEqual(expected, actual)

    def test_visible_row_trees(self):
        input_row = [0, 0, 1, 2, 4, 7, 4, 3]

        actual = visible_row_trees(input_row)
        expected = [True, False, True, True, True, True, False, False]

        self.assertEqual(expected, actual)

    def test_combine_tree_grid_masks(self):
        mask_1 = np.array([[True, False, True, True],
                           [True, True, False, False],
                           [True, False, False, False]])

        mask_2 = np.array([[True, False, True, True],
                           [False, False, False, True],
                           [True, False, False, False]])

        mask_3 = np.array([[True, False, True, True],
                           [True, True, False, False],
                           [False, True, True, False]])

        mask_4 = np.array([[True, False, True, True],
                           [True, True, False, False],
                           [False, True, True, False]])

        actual = combine_tree_grid_masks(mask_1, mask_2, mask_3, mask_4)
        expected = np.array([[True, False, True, True],
                             [True, True, False, True],
                             [True, True, True, False]])

        self.assertTrue(np.array_equal(expected, actual))

    def test_create_tree_grid_mask(self):
        tree_grid = create_tree_grid("testTrees.txt")

        actual_1, actual_2, actual_3, actual_4 = create_tree_grid_mask(
            tree_grid)

        # expected_1 = [[0, 1, 2, 8],
        #               [1, 1, 3, 2],
        #               [5, 1, 4, 6],
        #               [2, 2, 0, 0]]

        # rotated 90 degrees counter-clockwise
        # expected_2 = [[8, 2, 6, 0],
        #               [2, 3, 4, 0],
        #               [1, 1, 1, 2],
        #               [0, 1, 5, 2]]

        # rotated 90 degrees counter-clockwise
        # expected_3 = [[0, 0, 2, 2],
        #               [6, 4, 1, 5],
        #               [2, 3, 1, 1],
        #               [8, 2, 1, 0]]

        # rotated 90 degrees counter-clockwise
        # expected_4 = [[2, 5, 1, 0],
        #               [2, 1, 1, 1],
        #               [0, 4, 3, 2],
        #               [0, 6, 2, 8]]

        # Rolled counter-clockwise
        # expected_1 = [[True, True, True, True],
        #               [True, False, True, False],
        #               [True, False, False, True],
        #               [True, False, False, False]]
        # expected_2 = [[True, False, False, False],
        #               [True, True, True, False],
        #               [True, False, False, True],
        #               [True, True, True, False]]
        # expected_3 = [[True, False, True, False],
        #               [True, False, False, False],
        #               [True, True, False, False],
        #               [True, False, False, False]]
        # expected_4 = [[True, True, False, False],
        #               [True, False, False, False],
        #               [True, True, False, False],
        #               [True, True, False, True]]

        expected_1 = np.array([[True, True, True, True],
                               [True, False, True, False],
                               [True, False, False, True],
                               [True, False, False, False]])
        expected_2 = np.array([[True, True, True, True],
                               [True, False, True, False],
                               [True, False, True, False],
                               [False, True, False, False]])
        expected_3 = np.array([[False, False, False, True],
                               [False, False, True, True],
                               [False, False, False, True],
                               [False, True, False, True]])
        expected_4 = np.array([[False, False, False, True],
                               [False, False, False, False],
                               [True, False, True, True],
                               [True, True, True, True]])

        self.assertTrue(np.array_equal(expected_1, actual_1))
        self.assertTrue(np.array_equal(expected_2, actual_2))
        self.assertTrue(np.array_equal(expected_3, actual_3))
        self.assertTrue(np.array_equal(expected_4, actual_4))

    def test_count_visible_trees(self):
        visible_trees = count_visible_trees("testTrees.txt")
        expected = 14

        self.assertEqual(expected, visible_trees)

    ############ Part 2 Tests ##################

    def test_calculate_left_viewing_distance(self):
        tree_grid = create_tree_grid("testTrees.txt")
        tree_grid = np.array(tree_grid)
        row_index = 2
        col_index = 2
        tree = 4

        actual = calculate_left_viewing_distance(tree_grid, row_index, col_index - 1, tree)

        expected = 2

        self.assertEqual(expected, actual)

    def test_calculate_right_viewing_distance(self):
        tree_grid = create_tree_grid("testTrees.txt")
        tree_grid = np.array(tree_grid)
        row_index = 2
        col_index = 2
        tree = 4

        actual = calculate_right_viewing_distance(tree_grid, row_index,
                                                 col_index + 1, tree)

        expected = 1

        self.assertEqual(expected, actual)

    def test_calculate_top_viewing_distance(self):
        tree_grid = create_tree_grid("testTrees.txt")
        tree_grid = np.array(tree_grid)
        row_index = 2
        col_index = 2
        tree = 4

        actual = calculate_top_viewing_distance(tree_grid, row_index - 1,
                                                 col_index, tree)

        expected = 2

        self.assertEqual(expected, actual)

    def test_calculate_bottom_viewing_distance(self):
        tree_grid = create_tree_grid("testTrees.txt")
        tree_grid = np.array(tree_grid)
        row_index = 2
        col_index = 2
        tree = 4

        actual = calculate_bottom_viewing_distance(tree_grid, row_index + 1,
                                                col_index, tree)

        expected = 1

        self.assertEqual(expected, actual)

    def test_calculate_viewing_distances(self):
        tree_grid = create_tree_grid("testTrees.txt")

        actual = calculate_viewing_distances(tree_grid)

        expected = np.array([[1, 1, 2, 9],
                             [1, 1, 2, 1],
                             [6, 1, 4, 6],
                             [1, 6, 1, 1]])

        self.assertTrue(np.array_equal(expected, actual))

    def test_find_best_viewing_distance(self):
        actual = find_best_viewing_distance("testTrees.txt")

        expected = 9

        self.assertEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()
