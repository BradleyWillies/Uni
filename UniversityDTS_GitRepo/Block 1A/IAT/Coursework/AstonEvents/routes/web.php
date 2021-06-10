<?php

use App\Http\Controllers\Organiser\EventController;
use \App\Http\Controllers\StudentController;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', [\App\Http\Controllers\StudentController::class, 'index'])->name('student.event.index');
Route::get('/event/show/{id}', [StudentController::class, 'show'])->name('student.event.show');
Route::post('/event/show/{id}', [StudentController::class, 'addInterest'])->name('student.event.addInterest');

Route::prefix('/dashboard')->group(function () {
    Route::get('', [EventController::class, 'index'])->name('dashboard.event.index');
    Route::get('/event/create', [EventController::class, 'create'])->name('dashboard.event.create');
    Route::post('/event/store', [EventController::class, 'store'])->name('dashboard.event.store');
    Route::get('/event/show/{id}', [EventController::class, 'show'])->name('dashboard.event.show');
    Route::post('/event/edit/{id}', [EventController::class, 'edit'])->name('dashboard.event.edit');
});

Auth::routes();
