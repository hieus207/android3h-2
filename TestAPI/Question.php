<?php
class Question
{
  private $idquestion;
  private $type;
  private $content;
  private $image;
  private $da1;
  private $da2;
  private $da3;
  private $da4;
  private $dadung;
  public function __construct($idquestion,$type,$content,$image,$da1,$da2,$da3,$da4,$dadung)
  {
    $this->idquestion = $idquestion;
    $this->type = $type;
    $this->content = $content;
    $this->image = $image;
    $this->da1 = $da1;
    $this->da2 = $da2;
    $this->da3 = $da3;
    $this->da4 = $da4;
    $this->dadung = $dadung;
  }
 
}
?>