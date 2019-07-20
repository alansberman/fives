file_hand = open('words_alpha.txt',"r")

file_writer = open('fives_words.txt',"w")

for line in file_hand:
    # check word has only unique chars
    if len(set(line))==len(line):
        # only get words with 5 letters exactly
        if len(line)==6:
            file_writer.write(line)

file_writer.close()



