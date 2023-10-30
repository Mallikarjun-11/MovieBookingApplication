// import { HttpClient } from '@angular/common/http';
// import { Component, OnInit } from '@angular/core';
// import { HomeService } from 'src/app/services/home.service';
// import { Movie } from 'src/app/services/movie';

// @Component({
//   selector: 'app-home',
//   templateUrl: './home.component.html',
//   styleUrls: ['./home.component.css']
// })
// export class HomeComponent implements OnInit {

//   movies!:Movie[];
//   selectedMovie:string;
//   constructor(private homeService: HomeService) {}

//   ngOnInit(): void {
//     this.getMovies();  
//   }
//   uniqueMovieNames = new Set();
//   omdbResponse:any={};
  
//   private getMovies(){
//     this.homeService.getMoviesList().subscribe(data => {
//       this.movies = data;
//       console.log(this.movies);

     

//   // Create a new array with unique movie names
//     this.movies.filter(obj => {
//       if (!this.uniqueMovieNames.has(obj.movieName)) {
//           this.uniqueMovieNames.add(obj.movieName);
//           return true;
//       }
//       return false;
//   });
//       console.log("---------------------");
//       console.log(this.uniqueMovieNames);
      
      
//     });   

//   }

//   getImageUrl(movieName):void{
        
//       this.homeService.getOmdbResponse(movieName).subscribe(
//         response=>{
//           console.log(response);  
//           console.log(response.Poster);
//           return response.Poster;
                  
//         },
//         error=>{
//         console.log(error)
//         return '';
//         }
//       )
      
     
  
//   }

  
  

  




//   // onClick(movieName:string){
//   //   this.selectedMovie=movieName;
//   //   console.log(this.selectedMovie);    
//   // }

// }
import { Component, OnInit } from '@angular/core';
import { HomeService } from 'src/app/services/home.service';
import { LoginService } from 'src/app/services/login.service';
import { Movie } from 'src/app/services/movie';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  search:string='';
  movies: Movie[] = [];
  uniqueMovieNames: Set<string> = new Set();
  imageUrls: { [key: string]: string } = {}; // To store image URLs

  constructor(private homeService: HomeService,private loginService:LoginService) {}

  public loggedIn=false;
   public isAdmin;

  ngOnInit(): void {
    this.loggedIn=this.loginService.isLoggedIn();
    if(this.loginService.isLoggedIn()){
    this.loginService.isAdmin(localStorage.getItem('username')).subscribe((isAdmin) => {
      this.isAdmin = isAdmin;
    });      
  }
  this.getMovies();
}





  private getMovies() {
    this.homeService.getMoviesList().subscribe(data => {
      this.movies = data;
      console.log(this.movies);

      // Create a new array with unique movie names
      this.movies.forEach(obj => {
        this.uniqueMovieNames.add(obj.movieName);
      });
      console.log("---------------------");
      console.log(this.uniqueMovieNames);

      // Fetch image URLs for each unique movie
      this.uniqueMovieNames.forEach(movieName => {
        this.getImageUrl(movieName);
      });
    });
  }

  getImageUrl(movieName: string): void {
    this.homeService.getOmdbResponse(movieName).subscribe(
      response => {
        console.log(response);
        console.log(response.Poster);

        // Store the image URL in the imageUrls object
        this.imageUrls[movieName] = response.Poster;
      },
      error => {
        console.log(error);
      }
    );
  }

  delete(movieName: string):void{
    console.log("********************** in del");
    
    this.homeService.deleteMovie(movieName).subscribe(
      (resp:any)=>{

        console.log(resp);
       
          alert("Success: Movie deleted successfully");
          window.location.reload();
        
        
      },
      (err)=>{console.log(err);
      }
    );
  }
}
