using System;
using System.Diagnostics;

namespace cs_rb_tree{
    public enum Color{
        B,
        R
    }

    internal class Tree{
        private static void Main(){
            var tree=new Tree();
            var sw = new Stopwatch();
            sw.Start();
            for(var i=1;i<1000000;i++){
                tree.Insert(i);
            }
            tree.Search(555444);
            sw.Stop();
            Console.WriteLine("\nTime: {0}",sw.Elapsed);
        }
        private Node _root;
        private int _countRed;
        private void RotL(Node x){
            var y=x.Right;
            x.Right=y.Left;
            if(y.Left!=null){
                y.Left.Parent=x;
            }
            if(y!=null){
                y.Parent=x.Parent;
            }
            if(x.Parent!=null){
                if(x==x.Parent.Left){
                    x.Parent.Left=y;
                }
                else{
                    x.Parent.Right=y;
                }
            }
            else{
                _root=y;
            }
            y.Left=x;
            x.Parent=y;
        }
        private void RotR(Node x){
            var y=x.Left;
            x.Left=y.Right;
            if(y.Right!=null){
                y.Right.Parent=x;
            }
            if(y!=null){
                y.Parent=x.Parent;
            }
            if(x.Parent!=null){
                if(x==x.Parent.Right){
                    x.Parent.Right=y;
                }
                else{
                    x.Parent.Left=y;
                }
            }
            else{
                _root=y;
            }
            y.Right=x;
            x.Parent=y;
        }
        
        private void Display(){
            _countRed=0;
            if(_root==null){
                Console.WriteLine("Drzewo puste.");
                return;
            }
            Show(_root);
            Console.WriteLine();
            Console.ForegroundColor=ConsoleColor.Black;
            Console.Write("Czerwonych: {0}\nMaks. glebokosc: {1}\nMin. glebokosc: {2}\n", _countRed, MaxDepth(_root), MinDepth(_root));
        }
        private void Show(Node current){
            while(true){
                if(current==null) return;
                Show(current.Left);
                Console.ForegroundColor=current.Colour==Color.R?ConsoleColor.Red:ConsoleColor.Black;
                if(current.Colour==Color.R){
                    _countRed++;
                }
                Console.Write("({0}) ", current.Data);
                current=current.Right;
            }
        }
        private static int MaxDepth(Node node){
            if(node==null) return 0;
            var lDepth=MaxDepth(node.Left);
            var rDepth=MaxDepth(node.Right);

            if(lDepth>rDepth){
                return lDepth+1;
            }
            return rDepth+1;
        }
        private static int MinDepth(Node node){
            if(node==null) return 0;
            var lDepth=MinDepth(node.Left);
            var rDepth=MinDepth(node.Right);

            if(lDepth<rDepth){
                return lDepth+1;
            }
            return rDepth+1;
        }

        private void Search(int key){
            var isFound=false;
            var temp=_root;
            var bHeight=0;
            var height=1;
            while(!isFound){
                if(temp==null){
                    break;
                }
                if(temp.Colour==Color.B){
                    bHeight++;
                }
                if(key<temp.Data){
                    temp=temp.Left;
                    height++;
                }
                else if(key>temp.Data){
                    temp=temp.Right;
                    height++;
                }
                else if(key==temp.Data){
                    isFound=true;
                }
            }
            if(isFound){
                Console.WriteLine("\nZnaleziono {0}", temp.Data);
                Console.WriteLine("Glebokosc: {0}",height);
                Console.WriteLine(temp.Colour==Color.B?"Kolor Czarny":"Kolor Czerwony");
                Console.WriteLine("Czarna wyskosc: {0}", bHeight);
            }
            else{
                Console.WriteLine("Nie znaleziono {0}", key);
            }
        }
        
        private void Insert(int item){
            var newItem=new Node(item);
            if(_root==null){
                _root=newItem;
                _root.Colour=Color.B;
                return;
            }
            Node y=null;
            var x=_root;
            while(x!=null){
                y=x;
                x=newItem.Data<x.Data?x.Left:x.Right;
            }
            newItem.Parent=y;
            if(y==null){
                _root=newItem;
            }
            else if(newItem.Data<y.Data){
                y.Left=newItem;
            }
            else{
                y.Right=newItem;
            }
            newItem.Left=null;
            newItem.Right=null;
            newItem.Colour=Color.R;
            InsertFix(newItem);
        }
        private void InsertFix(Node inserted){
            while(inserted!=_root && inserted.Parent.Colour==Color.R){
                Node y;
                if(inserted.Parent==inserted.Parent.Parent.Left){
                    y=inserted.Parent.Parent.Right;
                    if(y!=null && y.Colour==Color.R){
                        inserted.Parent.Colour=Color.B;
                        y.Colour=Color.B;
                        inserted.Parent.Parent.Colour=Color.R;
                        inserted=inserted.Parent.Parent;
                    }
                    else{
                        if(inserted==inserted.Parent.Right){
                            inserted=inserted.Parent;
                            RotL(inserted);
                        }
                        inserted.Parent.Colour=Color.B;
                        inserted.Parent.Parent.Colour=Color.R;
                        RotR(inserted.Parent.Parent);
                    }
                }
                else{
                    y=inserted.Parent.Parent.Left;
                    if(y!=null && y.Colour==Color.R){
                        inserted.Parent.Colour=Color.B;
                        y.Colour=Color.B;
                        inserted.Parent.Parent.Colour=Color.R;
                        inserted=inserted.Parent.Parent;
                    }
                    else{
                        if(inserted==inserted.Parent.Left){
                            inserted=inserted.Parent;
                            RotR(inserted);
                        }
                        inserted.Parent.Colour=Color.B;
                        inserted.Parent.Parent.Colour=Color.R;
                        RotL(inserted.Parent.Parent);
                    }
                }
                _root.Colour=Color.B;
            }
        }
    }
}