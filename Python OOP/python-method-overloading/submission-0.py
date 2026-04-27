class TextProcessor:
    # Implement method overloading for format_text method
    def format_text(self,text1:str, *args):
        if len(args) == 0:
            return text1.upper()
        else:
            res = text1
            for text in args:
                res += text
            return res
            



# Don't modify the code below
processor = TextProcessor()
print(processor.format_text("hello"))
print(processor.format_text("hello", "world"))
