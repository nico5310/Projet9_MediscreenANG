export class Patient {

id!: number;
first_name!: string;
last_name!: string;
dob!: Date;
genre!: string;
address!: string;
phone!: string;


  constructor(first_name:string, last_name:string, dob:Date, genre:string, address:string, phone:string) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.dob = dob;
    this.genre = genre;
    this.address = address;
    this.phone = phone;
  }

}
