import os
import shutil
import sys
import time
import pygame
from playsound import playsound
from PIL import Image


def convert_to_ascii(image_path, max_width=100):
    """
    Convert an image to ASCII art.

    Args:
        image_path (str): Path to the image file.
        max_width (int): Maximum width of the ASCII art (default is 100).

    Returns:
        str: ASCII representation of the image.
    """

    console_width = shutil.get_terminal_size().columns
    width = min(int(console_width * 0.7), max_width)  # Limit to 80% of console width
    image = Image.open(image_path)

    # Calculate the aspect ratio of the input image
    aspect_ratio = image.width / image.height

    # Resize the image while preserving the aspect ratio
    new_width = width
    new_height = int(new_width / aspect_ratio)
    image = image.resize((new_width, new_height), resample=Image.BICUBIC)

    grayscale_image = image.convert("L")

    # Use a different ASCII character set
    ascii_chars = ['@', '#', 'S', '%', '?', '*', '+', ';', ':', ',', '.']

    ascii_art = ""
    for y in range(grayscale_image.height):
        for x in range(grayscale_image.width):
            pixel_value = grayscale_image.getpixel((x, y))
            ascii_art += ascii_chars[int(pixel_value / 255 * (len(ascii_chars) - 1))]
        ascii_art += "\n"

    return ascii_art


def animate_ascii_frames(image_folder, music_file):
    pygame.mixer.init()
    pygame.mixer.music.load(music_file)
    pygame.mixer.music.play()

    image_files = sorted(os.listdir(image_folder), key=lambda x: int(x.split('_')[-1].split('.')[0]))
    frame_delay = 1 / 60  #normal: 1 /24 1/60
    for image_file in image_files:
        if image_file.endswith(('.png', '.jpg', '.jpeg', '.gif')):
            image_path = os.path.join(image_folder, image_file)
            ascii_art = convert_to_ascii(image_path)
            clear_screen()
            print(ascii_art)
            time.sleep(frame_delay)



def clear_screen():
    """
    Clear the console screen.
    """
    if os.name == 'nt':
        os.system('cls')
    else:
        os.system('clear')


def main():
    # Prompt the user for the folder path
    # E:\Desktop\ASCII_Bad_Apple_Animated
    # E:\Desktop\ASCII_Bad_Apple_Animated\frames
    # E:\Desktop\ASCII_Bad_Apple_Animated\bad_Apple.wav
    folder_path = input("Enter the path to the folder containing image frames: ")

    # Check if the folder path is valid
    if not os.path.isdir(folder_path):
        print("Invalid folder path.")
        sys.exit(1)

    # Prompt the user for the music file path
    music_file = input("Enter the path to the music file: ")

    if not os.path.isfile(music_file):
        print("Invalid music file path.")
        sys.exit(1)

    animate_ascii_frames(folder_path, music_file)


if __name__ == "__main__":
    main()
