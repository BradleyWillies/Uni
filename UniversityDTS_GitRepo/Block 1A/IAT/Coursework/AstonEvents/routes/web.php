<?php

use App\Http\Controllers\Organiser\DashboardController;
use App\Http\Controllers\Organiser\EventController;
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

Route::get('/', function () {
    return view('welcome');
});

Auth::routes();

Route::get('/dashboard', [DashboardController::class, 'index'])->name('dashboard');
Route::get('/dashboard/event/create', [EventController::class, 'create'])->name('dashboard.event.create');
Route::post('/dashboard/event/store', [EventController::class, 'store'])->name('dashboard.event.store');
Route::post('/dashboard/event/show', [EventController::class, 'show'])->name('dashboard.event.show');
