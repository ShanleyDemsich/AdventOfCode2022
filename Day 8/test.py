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
        mask_1 = [[True, False, True, True],
                  [True, True, False, False],
                  [True, False, False, False]]

        mask_2 = [[True, False, True, True],
                  [False, False, False, True],
                  [True, False, False, False]]

        mask_3 = [[True, False, True, True],
                  [True, True, False, False],
                  [False, True, True, False]]

        mask_4 = [[True, False, True, True],
                  [True, True, False, False],
                  [False, True, True, False]]

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

        # rotated 90 degrees clockwise
        # expected_2 = [[2, 5, 1, 0],
        #               [2, 1, 1, 1],
        #               [0, 4, 3, 2],
        #               [0, 6, 2, 8]]

        # rotated 90 degrees clockwise
        # expected_3 = [[0, 0, 2, 2],
        #               [6, 4, 1, 5],
        #               [2, 3, 1, 1],
        #               [8, 2, 1, 0]]

        # rotated 90 degrees clockwise
        # expected_4 = [[8, 2, 6, 0],
        #               [2, 3, 4, 0],
        #               [1, 1, 1, 2],
        #               [0, 1, 5, 2]]

        ##############################################################

        # expected_1 = [[0, 1, 2, 8],
        #               [1, 1, 3, 2],
        #               [5, 1, 4, 6],
        #               [2, 2, 0, 0]]

        # rotated 90 degrees counter-clockwise
        # expected_2 = [[8, 2, 1, 0],
        #               [2, 3, 1, 1],
        #               [6, 4, 1, 5],
        #               [0, 0, 2, 2]]

        # rotated 90 degrees counter-clockwise
        # expected_3 = [[0, 1, 2, 8],
        #               [1, 1, 3, 2],
        #               [5, 1, 4, 6],
        #               [2, 2, 0, 0]]

        # rotated 90 degrees counter-clockwise
        # expected_4 = [[8, 2, 1, 0],
        #               [2, 3, 1, 1],
        #               [6, 4, 1, 5],
        #               [0, 0, 2, 2]]

        # Rolled clockwise
        # expected_1 = [[True, True, True, True],
        #               [True, False, True, False],
        #               [True, False, False, True],
        #               [True, False, False, False]]
        # expected_2 = [[True, True, False, False],
        #               [True, False, False, False],
        #               [True, True, False, False],
        #               [True, True, False, True]]
        # expected_3 = [[True, False, True, False],
        #               [True, False, False, False],
        #               [True, True, False, False],
        #               [True, False, False, False]]
        # expected_4 = [[True, False, False, False],
        #               [True, True, True, False],
        #               [True, False, False, True],
        #               [True, True, True, False]]

        expected_1 = [[True, True, True, True],
                      [True, False, True, False],
                      [True, False, False, True],
                      [True, False, False, False]]
        expected_2 = [[True, False, False, False],
                      [True, True, False, False],
                      [True, False, False, False],
                      [True, False, True, False]]
        expected_3 = [[True, True, True, True],
                      [True, False, True, False],
                      [True, False, False, True],
                      [True, False, False, False]]
        expected_4 = [[True, False, False, False],
                      [True, True, False, False],
                      [True, False, False, False],
                      [True, False, True, False]]

        self.assertEqual(expected_1, actual_1)
        # self.assertEqual(expected_2, actual_2)
        # self.assertEqual(expected_3, actual_3)
        # self.assertEqual(expected_4, actual_4)


if __name__ == '__main__':
    unittest.main()
