import pygame
import sys

import inputs


def show_path(lab, path, filename):
    size = len(lab)*40

    pygame.init()

    screen = pygame.display.set_mode((size, size))

    pygame.display.set_caption('Labirynth')

    def draw_lab(lab, path):
        block_size = 40
        for y in range(len(lab)):
            for x in range(len(lab)):
                rect = pygame.Rect(x*block_size, y*block_size,
                                   block_size, block_size)
                color = (0, 0, 0)
                if (lab[y][x]):
                    color = (255, 255, 255)
                if (x, y) in path:
                    color = (0, 255, 0)
                pygame.draw.rect(screen, color, rect, 0)

    draw_lab(lab, path)
    pygame.display.update()

    pygame.image.save(screen, filename)


show_path(inputs.labirynth1, [], 'empty_lab_1.jpg')
