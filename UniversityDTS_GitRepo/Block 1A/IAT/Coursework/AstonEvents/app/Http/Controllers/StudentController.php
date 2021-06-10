<?php

namespace App\Http\Controllers;

use App\Models\Event;
use App\Models\EventCategory;
use App\Models\Organiser;
use App\Models\User;
use Illuminate\Http\Request;

class StudentController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View|\Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $eventQuery = Event::query();

        if ($request->get('event_category_id')) {
            $eventQuery->where('event_category_id', $request->get('event_category_id'));
        }
        if ($request->get('event_heading')) {
            $sortOrder = $request->get('sort') ?? 'DESC';
            $eventQuery->orderBy($request->get('event_heading'), $sortOrder);
        }

        $events = $eventQuery->get();
        $eventCategories = EventCategory::all();
        return view('index', compact('events', 'eventCategories'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View|\Illuminate\Http\Response
     */
    public function show($id)
    {
        $event = Event::with('images')->where('id', $id)->first();
        $eventCategory = EventCategory::where('id', $event->event_category_id)->first();
        $organiser = Organiser::where('id', $event->organiser_id)->first();
        $organiserEmail = (User::where('id', $organiser->user_id)->first())->email;
        return view('event.show', compact('event', 'eventCategory', 'organiser', 'organiserEmail'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

    /**
     * Adds 1 to the interest ranking of the event with the id of $id
     *
     * @param $id
     */
    public function addInterest($id)
    {
        $event = Event::find($id);
        $event->interest_ranking += 1;
        $event->save();
        $this->show($id);
    }
}
