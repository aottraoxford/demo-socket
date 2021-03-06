import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { of } from 'rxjs';

import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  isLogin = true;
  form: FormGroup;
  display = false;
  message = null;
  statusCode = 400;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.form = this.fb.group({
      phoneNumber: new FormControl("+855"),
      password: new FormControl,
      confirmPassword: new FormControl
    });
  }

  ngOnInit(): void { }

  toLogin() {
    this.form.reset();
    this.form.patchValue({
      phoneNumber: "+855"
    });
    this.isLogin = true;
    return false;
  }
  toRegister() {
    this.form.reset()
    this.form.patchValue({
      phoneNumber: "+855"
    });
    this.isLogin = false;
    return false;
  }

  onRegister() {
    const obj = this.form.value
    this.authService.register(obj).pipe(catchError(error => {
      this.message = error.error.message
      this.display = true;
      return of([]);
    })).subscribe((res: any) => {
      this.message = res.message
      this.statusCode = res.status
      this.display = true;
    })
    
  }

  onLogin() {
    const obj = this.form.value
    this.authService.login(obj).pipe(catchError(error => {
      this.message = error.error.message
      this.display = true;
      return of ([]);
    })).subscribe((res: any) => {
      this.router.navigateByUrl("dashboard")
    })
    
  }

}
