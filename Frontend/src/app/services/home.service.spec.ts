import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';



import { HomeService } from './home.service';
import { MOVIES } from '../mock-data/movies';

describe('HomeService', () => {
  let service: HomeService;
  let testingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(HomeService);
    testingController=TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all movies',() =>{
    service.getMoviesList().subscribe((movies:any)=>{
      expect(movies).toBeTruthy();
      expect(movies.length).toBe(5);
      
    });
    const mockReq=testingController.expectOne("http://ec2-54-80-208-30.compute-1.amazonaws.com:5000/api/v1/moviebooking/all");
    expect(mockReq.request.method).toEqual('GET');
    mockReq.flush(Object.values(MOVIES));
  })
});
