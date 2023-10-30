import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Theatre } from 'src/app/services/theatre';
import { TheatresService } from 'src/app/services/theatres.service';


@Component({
  selector: 'app-theatres',
  templateUrl: './theatres.component.html',
  styleUrls: ['./theatres.component.css']
})
export class TheatresComponent implements OnInit {
  movieName:string;
  theatres:Theatre[];

  constructor(private route:ActivatedRoute,private theatresService:TheatresService) { }

  ngOnInit(): void {
    // this.route.queryParams
    // .subscribe(params=>{
    //   console.log(params);
    //   this.movieName=params['movie'];
    //   console.log(this.movieName);
      
    // })
    this.movieName=this.route.snapshot.paramMap.get('movie');
    console.log(this.movieName);

    this.getTheatres();
  
    
    
  }


  getTheatres(){
    this.theatresService.getTheatres(this.movieName).subscribe(data=>{
      this.theatres=data;
      console.log(this.theatres);
      
    });
  }

}
