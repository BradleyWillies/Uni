<?php

namespace App\Http\Controllers\Organiser;

use App\Http\Controllers\Controller;
use App\Http\Requests\EventRequest;
use App\Models\Event;
use App\Models\EventCategory;
use Illuminate\Http\Request;

class EventController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function index()
    {
        $events = Event::where('organiser_id', '=', auth()->user()->organiser->id)->get();
        return view('organiser.dashboard', compact('events'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function create()
    {
        $eventCategories = EventCategory::all();
        return view('organiser.event.create', compact('eventCategories'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  EventRequest  $request
     * @return \Illuminate\Http\RedirectResponse
     */
    public function store(EventRequest $request)
    {
        $event = new Event();
        $event->fill($request->all());
        $event->organiser_id = auth()->user()->organiser->id;
        $event->save();
        return redirect()->route("dashboard.event.index")->with("success", "Event created");
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function show($id)
    {
        $event = Event::find($id);
//        dd($event->date_time);
        $eventCategories = EventCategory::all();
        return view('organiser.event.show', compact('event', 'eventCategories'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  EventRequest $request
     * @param int $id
     * @return \Illuminate\Http\RedirectResponse
     */
    public function edit(EventRequest $request, int $id)
    {
        $event = Event::find($id);
        $event->update($request->all());
        return redirect()->route("dashboard.event.index")->with("success", "Event updated");
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
}
